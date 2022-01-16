package tr.start.point;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("tr.start.point");

        noClasses()
            .that()
            .resideInAnyPackage("tr.start.point.service..")
            .or()
            .resideInAnyPackage("tr.start.point.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..tr.start.point.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
