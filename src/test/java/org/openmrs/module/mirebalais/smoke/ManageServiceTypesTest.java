package org.openmrs.module.mirebalais.smoke;

import org.junit.Test;
import org.openmrs.module.mirebalais.smoke.helper.NameGenerator;
import org.openmrs.module.mirebalais.smoke.pageobjects.AppDashboard;
import org.openmrs.module.mirebalais.smoke.pageobjects.ServiceTypeApp;

import static junit.framework.Assert.assertEquals;

public class ManageServiceTypesTest extends DbTest {

    @Test
    public void systemAdminCanAddServiceType() throws Exception {
        AppDashboard appDashboard = new AppDashboard(driver);
        ServiceTypeApp serviceTypeApp = new ServiceTypeApp(driver);

        logInAsAdmin();

        appDashboard.openManageAppointmentsApp();
        int expectedAmountOfServiceTypes = serviceTypeApp.getTotalAmountOfServiceTypes() + 1;

        serviceTypeApp.openNewServiceType();
        serviceTypeApp.createServiceType(NameGenerator.getServiceTypeName(), "20", "Description");

        assertEquals(expectedAmountOfServiceTypes, serviceTypeApp.getTotalAmountOfServiceTypes());
    }
}
