package com.capgemini.service;

import com.capgemini.dto.BasicResponse;
import com.capgemini.entity.*;
import com.capgemini.repo.ApplicationsRepository;
import com.capgemini.repo.ChallanRepository;
import com.capgemini.repo.DrivingLicenseRepository;
import com.capgemini.repo.RTOOfficerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Driver;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RTOServiceImplTest {

    @Mock
    RTOOfficerRepository rtoOfficerRepository;

    @Mock
    DrivingLicenseRepository drivingLicenseRepository;

    @Mock
    ChallanRepository challanRepository;

    @Mock
    ApplicationsRepository applicationsRepository;


    @Spy
    @InjectMocks
    RTOServiceImpl rtoService;

    EmailService emailService;


    @Test
    @Order(1)
    void login() {

        String email = "dev123@gmail.com";
        String password = "dev1234@";

        RTOOfficer rtoOfficer = new RTOOfficer();
        rtoOfficer.setEmail(email);
        rtoOfficer.setPassword(password);
        rtoOfficer.setUsername("dev@123");
        Optional<RTOOfficer> rto = Optional.of(rtoOfficer);
        when(rtoOfficerRepository.findById(email)).thenReturn(rto);
        BasicResponse response = rtoService.login(rtoOfficer);
        Assertions.assertEquals(true, response.isStatus());

    }

    @Test
    @Order(2)
    void getAllPendingApplications() {
        String email = "mdkashif@gmail.com";
        String password = "mdkashif@1234";
        String applicationNo = "MH1234";

        Application application = new Application();
        application.setApplicationNumber(applicationNo);

        Applicant applicant = new Applicant();
        Date date = new Date(01/02/2000);
        applicant.setDateOfBirth(date);
        applicant.setEmail(email);
        applicant.setFirstName("Md");
        applicant.setLastName("Ali");
        applicant.setMiddleName("Kashif");
        applicant.setGender(Gender.MALE);
        applicant.setPlaceOfBirth("Mumbai");
        applicant.setQualification("Graduation");
        applicant.setMobile("8978456532");
        applicant.setNationality("Indian");
        application.setApplicant(applicant);

        Address address = new Address();
        address.setCity("Mumbai");
        address.setState("Maharastra");
        address.setPincode("400065");
        address.setHouse("554");
        applicant.setPresentAddress(address);
        applicant.setPermanentAddress(address);

        applicant.setVehicleNumber("Mh8T5648");
        applicant.setVehicleType("car");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        applicant.setUser(user);

        Date date1 = new Date(20/04/2021);
        application.setApplicationDate(date1);

        RTOOffice rtoOffice = new RTOOffice();
        rtoOffice.setRtoId(4411);
        rtoOffice.setRtoName("Dev");
        application.setRtoOffice(rtoOffice);

        application.setType(ApplicationType.LL);

        Documents documents = new Documents();
        documents.setPhoto("Picture");
        documents.setAddressProof("Aadhaar");
        documents.setIdProof("Pan");
        application.setDocuments(documents);

        application.setModeOfPayment("Online");
        application.setAmountPaid(500);
        application.setPaymentStatus("Paid");

        Appointment appointment = new Appointment();
        appointment.setAppointmentNumber("2356");
        appointment.setAppointmentNumber("2897LK");
        Date dateTest = new Date(07-06-2021);
        appointment.setTestDate(dateTest);
        appointment.setTestResult("Pending");
        appointment.setTimeSlot("Morning");

        RTOOfficer rtoOfficer = new RTOOfficer();
        rtoOfficer.setOffice(rtoOffice);
        rtoOfficer.setEmail("dev123@gmail.com");
        rtoOfficer.setUsername("dev@123");
        rtoOfficer.setPassword("dev1234@");
        appointment.setApprover(rtoOfficer);

        application.setAppointment(appointment);

        application.setStatus(ApplicationStatus.PENDING);
        application.setRemarks("application for LL");

        List<Application> applications = mock(List.class);
        Optional<Application> optionalApplication = Optional.of(application);
        when(applicationsRepository.findAll()).thenReturn(applications);
        List<Application> result = rtoService.getAllApprovedApplications();
        Assertions.assertNotNull(result);

    }

    @Test
    @Order(3)
    void getAllRejectedApplications() {
        String email = "mdkashif@gmail.com";
        String password = "mdkashif@1234";
        String applicationNo = "MH1234";

        Application application = new Application();
        application.setApplicationNumber(applicationNo);

        Applicant applicant = new Applicant();
        Date date = new Date(01/02/2000);
        applicant.setDateOfBirth(date);
        applicant.setEmail(email);
        applicant.setFirstName("Md");
        applicant.setLastName("Ali");
        applicant.setMiddleName("Kashif");
        applicant.setGender(Gender.MALE);
        applicant.setPlaceOfBirth("Mumbai");
        applicant.setQualification("Graduation");
        applicant.setMobile("8978456532");
        applicant.setNationality("Indian");
        application.setApplicant(applicant);

        Address address = new Address();
        address.setCity("Mumbai");
        address.setState("Maharastra");
        address.setPincode("400065");
        address.setHouse("554");
        applicant.setPresentAddress(address);
        applicant.setPermanentAddress(address);

        applicant.setVehicleNumber("Mh8T5648");
        applicant.setVehicleType("car");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        applicant.setUser(user);

        Date date1 = new Date(20/04/2021);
        application.setApplicationDate(date1);

        RTOOffice rtoOffice = new RTOOffice();
        rtoOffice.setRtoId(4411);
        rtoOffice.setRtoName("Dev");
        application.setRtoOffice(rtoOffice);

        application.setType(ApplicationType.LL);

        Documents documents = new Documents();
        documents.setPhoto("Picture");
        documents.setAddressProof("Aadhaar");
        documents.setIdProof("Pan");
        application.setDocuments(documents);

        application.setModeOfPayment("Online");
        application.setAmountPaid(500);
        application.setPaymentStatus("Paid");

        Appointment appointment = new Appointment();
        appointment.setAppointmentNumber("2356");
        appointment.setAppointmentNumber("2897LK");
        Date dateTest = new Date(07-06-2021);
        appointment.setTestDate(dateTest);
        appointment.setTestResult("Pending");
        appointment.setTimeSlot("Morning");

        RTOOfficer rtoOfficer = new RTOOfficer();
        rtoOfficer.setOffice(rtoOffice);
        rtoOfficer.setEmail("dev123@gmail.com");
        rtoOfficer.setUsername("dev@123");
        rtoOfficer.setPassword("dev1234@");
        appointment.setApprover(rtoOfficer);

        application.setAppointment(appointment);

        application.setStatus(ApplicationStatus.REJECTED);
        application.setRemarks("application for LL");

        List<Application> applications = mock(List.class);
        Optional<Application> optionalApplication = Optional.of(application);
        when(applicationsRepository.findAll()).thenReturn(applications);
        List<Application> result = rtoService.getAllApprovedApplications();
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(4)
    void getAllApprovedApplications() {
        String email = "mdkashif@gmail.com";
        String password = "mdkashif@1234";
        String applicationNo = "MH1234";

        Application application = new Application();
        application.setApplicationNumber(applicationNo);

        Applicant applicant = new Applicant();
        Date date = new Date(01/02/2000);
        applicant.setDateOfBirth(date);
        applicant.setEmail(email);
        applicant.setFirstName("Md");
        applicant.setLastName("Ali");
        applicant.setMiddleName("Kashif");
        applicant.setGender(Gender.MALE);
        applicant.setPlaceOfBirth("Mumbai");
        applicant.setQualification("Graduation");
        applicant.setMobile("8978456532");
        applicant.setNationality("Indian");
        application.setApplicant(applicant);

        Address address = new Address();
        address.setCity("Mumbai");
        address.setState("Maharastra");
        address.setPincode("400065");
        address.setHouse("554");
        applicant.setPresentAddress(address);
        applicant.setPermanentAddress(address);

        applicant.setVehicleNumber("Mh8T5648");
        applicant.setVehicleType("car");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        applicant.setUser(user);

        Date date1 = new Date(20/04/2021);
        application.setApplicationDate(date1);

        RTOOffice rtoOffice = new RTOOffice();
        rtoOffice.setRtoId(4411);
        rtoOffice.setRtoName("Dev");
        application.setRtoOffice(rtoOffice);

        application.setType(ApplicationType.LL);

        Documents documents = new Documents();
        documents.setPhoto("Picture");
        documents.setAddressProof("Aadhaar");
        documents.setIdProof("Pan");
        application.setDocuments(documents);

        application.setModeOfPayment("Online");
        application.setAmountPaid(500);
        application.setPaymentStatus("Paid");

        Appointment appointment = new Appointment();
        appointment.setAppointmentNumber("2356");
        appointment.setAppointmentNumber("2897LK");
        Date dateTest = new Date(07-06-2021);
        appointment.setTestDate(dateTest);
        appointment.setTestResult("Pending");
        appointment.setTimeSlot("Morning");

        RTOOfficer rtoOfficer = new RTOOfficer();
        rtoOfficer.setOffice(rtoOffice);
        rtoOfficer.setEmail("dev123@gmail.com");
        rtoOfficer.setUsername("dev@123");
        rtoOfficer.setPassword("dev1234@");
        appointment.setApprover(rtoOfficer);

        application.setAppointment(appointment);

        application.setStatus(ApplicationStatus.APPROVED);
        application.setRemarks("application for LL");

        List<Application> applications = mock(List.class);
        Optional<Application> optionalApplication = Optional.of(application);
        when(applicationsRepository.findAll()).thenReturn(applications);
        List<Application> result = rtoService.getAllApprovedApplications();
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(5)
    void viewApplicationById() {
        String email = "mdkashif@gmail.com";
        String password = "mdkashif@1234";
        String applicationNo = "MH1234";

        Application application = new Application();
        application.setApplicationNumber(applicationNo);

        Applicant applicant = new Applicant();
        Date date = new Date(01/02/2000);
        applicant.setDateOfBirth(date);
        applicant.setEmail(email);
        applicant.setFirstName("Md");
        applicant.setLastName("Ali");
        applicant.setMiddleName("Kashif");
        applicant.setGender(Gender.MALE);
        applicant.setPlaceOfBirth("Mumbai");
        applicant.setQualification("Graduation");
        applicant.setMobile("8978456532");
        applicant.setNationality("Indian");
        application.setApplicant(applicant);

        Address address = new Address();
        address.setCity("Mumbai");
        address.setState("Maharastra");
        address.setPincode("400065");
        address.setHouse("554");
        applicant.setPresentAddress(address);
        applicant.setPermanentAddress(address);

        applicant.setVehicleNumber("Mh8T5648");
        applicant.setVehicleType("car");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        applicant.setUser(user);

        Date date1 = new Date(20/04/2021);
        application.setApplicationDate(date1);

        RTOOffice rtoOffice = new RTOOffice();
        rtoOffice.setRtoId(4411);
        rtoOffice.setRtoName("Dev");
        application.setRtoOffice(rtoOffice);

        application.setType(ApplicationType.LL);

        Documents documents = new Documents();
        documents.setPhoto("Picture");
        documents.setAddressProof("Aadhaar");
        documents.setIdProof("Pan");
        application.setDocuments(documents);

        application.setModeOfPayment("Online");
        application.setAmountPaid(500);
        application.setPaymentStatus("Paid");

        Appointment appointment = new Appointment();
        appointment.setAppointmentNumber("2356");
        appointment.setAppointmentNumber("2897LK");
        Date dateTest = new Date(07-06-2021);
        appointment.setTestDate(dateTest);
        appointment.setTestResult("Pending");
        appointment.setTimeSlot("Morning");

        RTOOfficer rtoOfficer = new RTOOfficer();
        rtoOfficer.setOffice(rtoOffice);
        rtoOfficer.setEmail("dev123@gmail.com");
        rtoOfficer.setUsername("dev@123");
        rtoOfficer.setPassword("dev1234@");
        appointment.setApprover(rtoOfficer);

        application.setAppointment(appointment);

        application.setStatus(ApplicationStatus.APPROVED);
        application.setRemarks("application for LL");

        when(applicationsRepository.getOne(applicationNo)).thenReturn(application);
        Application response = rtoService.viewApplicationById(applicationNo);
        Assertions.assertEquals("MH1234", response.getApplicationNumber());

    }

    @Test
    @Order(6)
    void checkChallanByVehicleNumber() {
        String vehicleNumber = "MH5D1287";
        String vehicleChallan = "56MH5D1287";

        Challan challan = mock(Challan.class);
        List<Challan> challans = mock(List.class);


        challan.setVehicleNumber(vehicleNumber);
        challan.setChallanNumber(vehicleChallan);
        challan.setAmount(500);

        challans.add(1, challan);


        Optional<Challan> optionalChallan = Optional.of(challan);
        when(challanRepository.findAll()).thenReturn(challans);
        List<Challan> result = rtoService.checkChallanByVehicleNumber(vehicleNumber);
        Assertions.assertNotNull(result);

    }

    @Test
    @Order(7)
    void checkAllChallan() {

    }

    @Test
    @Order(8)
    void modifyTestResultById() {
        String applicationNo = "MH1234";
        Application application = new Application();
        application.setStatus(ApplicationStatus.APPROVED);
        application.setApplicationNumber(applicationNo);

        Optional<Application> optionalApplication = Optional.of(application);
        when(applicationsRepository.findById(applicationNo)).thenReturn(optionalApplication);
        Application result = rtoService.modifyTestResultById(applicationNo, ApplicationStatus.APPROVED);
        Assertions.assertEquals(null, result);

    }

    @Test
    @Order(9)
    void generateLearnerLicense() {
        String applicationNo = "MH1234";
        String rtoEmail = "dev123@gmail.com";
        String drivingLicenseNo = "MH12 MU78985321";
        Application application = new Application();
        application.setStatus(ApplicationStatus.APPROVED);
        application.setApplicationNumber(applicationNo);
        application.setType(ApplicationType.DL);

        RTOOfficer rtoOfficer = new RTOOfficer();
        rtoOfficer.setEmail(rtoEmail);

        RTOOffice rtoOffice = new RTOOffice();

        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setDrivingLicenseNumber(drivingLicenseNo);
        Date dateValidTill = new Date(2/9/2050);
        drivingLicense.setValidTill(dateValidTill);
        drivingLicense.setIssuedBy(rtoOffice);
        drivingLicense.setApplication(application);
        Date dateIssue = new Date(06/7/2000);
        drivingLicense.setDateOfIssue(dateIssue);

        DrivingLicense saved = mock(DrivingLicense.class);
        DrivingLicense result = drivingLicenseRepository.save(drivingLicense);

        Assertions.assertEquals(null, result);
        verify(drivingLicenseRepository).save(drivingLicense);
    }

    @Test@Order(10)
    void generateDrivingLicense() {
        String applicationNo = "MH1234";
        String rtoEmail = "dev123@gmail.com";
        String drivingLicenseNo = "MH12 MU78985321";
        Application application = new Application();
        application.setStatus(ApplicationStatus.APPROVED);
        application.setApplicationNumber(applicationNo);
        application.setType(ApplicationType.LL);

        RTOOfficer rtoOfficer = new RTOOfficer();
        rtoOfficer.setEmail(rtoEmail);

        RTOOffice rtoOffice = new RTOOffice();

        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setDrivingLicenseNumber(drivingLicenseNo);
        Date dateValidTill = new Date(2/9/2050);
        drivingLicense.setValidTill(dateValidTill);
        drivingLicense.setIssuedBy(rtoOffice);
        drivingLicense.setApplication(application);
        Date dateIssue = new Date(06/7/2000);
        drivingLicense.setDateOfIssue(dateIssue);

        DrivingLicense saved = mock(DrivingLicense.class);
        DrivingLicense result = drivingLicenseRepository.save(drivingLicense);

        Assertions.assertEquals(null, result);
        verify(drivingLicenseRepository).save(drivingLicense);
    }

    @Test
    @Order(11)
    void emailLicense() {
        String email = "dev123@gmail.com";
        String drivingLicenseNo = "MH12 MU78985321";

        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setDrivingLicenseNumber(drivingLicenseNo);
    }


    @Test
    @Order(12)
    void testEmailLicense() {
    }
}