package hh.harjoitustyo.knitapp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.harjoitustyo.knitapp.domain.Category;
import hh.harjoitustyo.knitapp.domain.CategoryRepository;
import hh.harjoitustyo.knitapp.domain.Project;
import hh.harjoitustyo.knitapp.domain.ProjectRepository;
import hh.harjoitustyo.knitapp.domain.Status;
import hh.harjoitustyo.knitapp.domain.StatusRepository;
import hh.harjoitustyo.knitapp.domain.Yarn;
import hh.harjoitustyo.knitapp.domain.YarnRepository;

@SpringBootApplication
public class KnitappApplication {

	private final ProjectRepository projectRepository;

	private static final Logger log = LoggerFactory.getLogger(KnitappApplication.class);

	KnitappApplication(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(KnitappApplication.class, args);
	}

	@Bean
	public CommandLineRunner ProjectTestData(
			ProjectRepository projectRepository,
			CategoryRepository categoryRepository,
			StatusRepository statusRepository,
			YarnRepository yarnRepository) {

		return (args) -> {
			// create selectable entities for categories
			Category category1 = new Category("Koiralle");
			Category category2 = new Category("Pipo");
			Category category3 = new Category("Villapaita");
			Category category4 = new Category("Sukat");
			Category category5 = new Category("Huivi");

			// create selectable entities for statuses
			Status status1 = new Status("Ei aloitettu");
			Status status2 = new Status("Kesken");
			Status status3 = new Status("Valmis");

			// save an example yarn
			Yarn yarn1 = new Yarn("Kotikulta Tuokio", "Worsted", "1175 Punainen", "4 mm", "18s x 26krs");
			yarnRepository.save(yarn1);
			Yarn managedYarn1 = yarnRepository.findById(yarn1.getYarnId()).orElseThrow();

			log.info("Save one project as an example");

			// save category and status first
			categoryRepository.save(category1);
			statusRepository.save(status2);

			// create new project and link category and status
			Project project1 = new Project();
			project1.setProjectName("Villapaita Sarialle");
			project1.setSize("L");
			project1.setNeedles("4,5 mm pyöröpuikot, 4 mm pyöröpuikot");
			project1.setGaugeSize("20 s x 26 krs");
			project1.setNotes("Aloitettu lokakuussa 2025");
			project1.setCategory(category1.getCategoryName());
			project1.setStatus(status2.getStatusName());
			project1.setPattern("https://drive.google.com/file/d/1vIDfyZUub2B4L46cYj2T_Q_qWqk2_lto/view");
			project1.setYarns(List.of(managedYarn1));

			// save project as test data
			projectRepository.save(project1);

			// save all categories and statuses
			categoryRepository.save(category1);
			categoryRepository.save(category2);
			categoryRepository.save(category3);
			categoryRepository.save(category4);
			categoryRepository.save(category5);

			statusRepository.save(status1);
			statusRepository.save(status2);
			statusRepository.save(status3);

		};
	}

}
