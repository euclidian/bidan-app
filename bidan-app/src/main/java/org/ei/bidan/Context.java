package org.ei.bidan;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import org.ei.bidan.bidan.domain.Anak;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.repository.AnakRepository;
import org.ei.bidan.bidan.service.AnakService;
import org.ei.bidan.bidan.service.formSubmissionHandler.AnakBayiRegistrationHandler;
import org.ei.bidan.bidan.service.formSubmissionHandler.AnakCloseHandler;
import org.ei.bidan.bidan.service.formSubmissionHandler.KIANCCloseHandler;
import org.ei.bidan.bidan.service.formSubmissionHandler.KIPNCCloseHandler;
import org.ei.bidan.bidan.view.contract.KBClients;
import org.ei.bidan.bidan.view.contract.KartuIbuPNCClients;
import org.ei.bidan.bidan.view.controller.AnakRegisterController;
import org.ei.bidan.bidan.view.controller.KartuIbuANCRegisterController;
import org.ei.bidan.bidan.view.controller.KartuIbuPNCRegisterController;
import org.ei.bidan.bidan.view.controller.KartuIbuRegisterController;
import org.ei.bidan.repository.AlertRepository;
import org.ei.bidan.repository.AllAlerts;
import org.ei.bidan.repository.AllBeneficiaries;
import org.ei.bidan.repository.AllTimelineEvents;
import org.ei.bidan.repository.ChildRepository;
import org.ei.bidan.service.ActionService;
import org.ei.bidan.service.ChildService;
import org.ei.bidan.service.ServiceProvidedService;
import org.ei.bidan.service.UserService;
import org.ei.bidan.service.ZiggyFileLoader;
import org.ei.bidan.service.ZiggyService;
import org.ei.bidan.service.formSubmissionHandler.ANCRegistrationOAHandler;
import org.ei.bidan.service.formSubmissionHandler.ANCVisitHandler;
import org.ei.bidan.service.formSubmissionHandler.ChildRegistrationOAHandler;
import org.ei.bidan.service.formSubmissionHandler.DeliveryPlanHandler;
import org.ei.bidan.service.formSubmissionHandler.FormSubmissionRouter;
import org.ei.bidan.view.contract.ANCClients;
import org.ei.bidan.view.contract.ECClients;
import org.ei.bidan.view.contract.FPClients;
import org.ei.bidan.view.contract.SmartRegisterClients;
import org.ei.bidan.view.contract.Villages;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.repository.IbuRepository;
import org.ei.bidan.bidan.service.BidanService;
import org.ei.bidan.bidan.service.IbuService;
import org.ei.bidan.bidan.service.formSubmissionHandler.KartuIbuANCRegistrationHandler;
import org.ei.bidan.bidan.view.contract.BidanHomeContext;
import org.ei.bidan.bidan.view.contract.KartuIbuANCClients;
import org.ei.bidan.bidan.view.contract.KartuIbuClients;
import org.ei.bidan.bidan.service.formSubmissionHandler.KartuIbuCloseHandler;
import org.ei.bidan.bidan.service.formSubmissionHandler.KartuIbuRegistrationHandler;
import org.ei.bidan.bidan.repository.KartuIbuRepository;
import org.ei.bidan.bidan.service.KartuIbuService;
import org.ei.bidan.bidan.view.controller.BidanController;
import org.ei.bidan.repository.*;
import org.ei.bidan.service.*;
import org.ei.bidan.service.formSubmissionHandler.*;
import org.ei.bidan.sync.SaveANMLocationTask;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.Session;
import org.ei.bidan.view.contract.*;
import org.ei.bidan.view.contract.pnc.PNCClients;
import org.ei.bidan.view.controller.ANMController;
import org.ei.bidan.view.controller.ANMLocationController;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Context {
    private android.content.Context applicationContext;
    private static Context context = new Context();

    private Repository repository;
    private EligibleCoupleRepository eligibleCoupleRepository;
    private AlertRepository alertRepository;
    private SettingsRepository settingsRepository;
    private ChildRepository childRepository;
    private MotherRepository motherRepository;
    private TimelineEventRepository timelineEventRepository;
    private ReportRepository reportRepository;
    private FormDataRepository formDataRepository;
    private ServiceProvidedRepository serviceProvidedRepository;
    private KartuIbuRepository kartuIbuRepository;
    private IbuRepository ibuRepository;
    private AnakRepository anakRepository;

    private AllSettings allSettings;
    private AllSharedPreferences allSharedPreferences;
    private AllAlerts allAlerts;
    private AllEligibleCouples allEligibleCouples;
    private AllBeneficiaries allBeneficiaries;
    private AllTimelineEvents allTimelineEvents;
    private AllReports allReports;
    private AllServicesProvided allServicesProvided;
    private AllKartuIbus allKartuIbus;
    private AllKohort allKohort;

    private DrishtiService drishtiService;
    private ActionService actionService;
    private FormSubmissionService formSubmissionService;
    private FormSubmissionSyncService formSubmissionSyncService;
    private ZiggyService ziggyService;
    private UserService userService;
    private AlertService alertService;
    private EligibleCoupleService eligibleCoupleService;
    private MotherService motherService;
    private ChildService childService;
    private ANMService anmService;
    private BeneficiaryService beneficiaryService;
    private ServiceProvidedService serviceProvidedService;
    private PendingFormSubmissionService pendingFormSubmissionService;
    private KartuIbuService kartuIbuService;
    private BidanService bidanService;
    private IbuService ibuService;
    private AnakService anakService;

    private Session session;
    private Cache<String> listCache;
    private Cache<SmartRegisterClients> smartRegisterClientsCache;
    private Cache<HomeContext> homeContextCache;
    private Cache<ECClients> ecClientsCache;
    private Cache<FPClients> fpClientsCache;
    private Cache<ANCClients> ancClientsCache;
    private Cache<PNCClients> pncClientsCache;
    private Cache<Villages> villagesCache;
    private Cache<Typeface> typefaceCache;
    private Cache<KartuIbuClients> kartuIbuClientsCache;
    private Cache<BidanHomeContext> bidanHomeContextCache;
    private Cache<KartuIbuANCClients> kartuIbuANCClientsCache;
    private Cache<KartuIbuPNCClients> kartuIbuPNCClientsCache;
    private Cache<KBClients> kbClientsCache;

    private HTTPAgent httpAgent;
    private ZiggyFileLoader ziggyFileLoader;

    private FormSubmissionRouter formSubmissionRouter;
    private ECRegistrationHandler ecRegistrationHandler;
    private FPComplicationsHandler fpComplicationsHandler;
    private FPChangeHandler fpChangeHandler;
    private RenewFPProductHandler renewFPProductHandler;
    private ECCloseHandler ecCloseHandler;
    private ANCRegistrationHandler ancRegistrationHandler;
    private ANCRegistrationOAHandler ancRegistrationOAHandler;
    private ANCVisitHandler ancVisitHandler;
    private ANCCloseHandler ancCloseHandler;
    private TTHandler ttHandler;
    private IFAHandler ifaHandler;
    private HBTestHandler hbTestHandler;
    private DeliveryOutcomeHandler deliveryOutcomeHandler;
    private DeliveryPlanHandler deliveryPlanHandler;
    private PNCRegistrationOAHandler pncRegistrationOAHandler;
    private PNCCloseHandler pncCloseHandler;
    private PNCVisitHandler pncVisitHandler;
    private ChildImmunizationsHandler childImmunizationsHandler;
    private ChildRegistrationECHandler childRegistrationECHandler;
    private ChildRegistrationOAHandler childRegistrationOAHandler;
    private ChildCloseHandler childCloseHandler;
    private ChildIllnessHandler childIllnessHandler;
    private VitaminAHandler vitaminAHandler;
    private ECEditHandler ecEditHandler;
    private ANCInvestigationsHandler ancInvestigationsHandler;
    private SaveANMLocationTask saveANMLocationTask;
    private KartuIbuRegistrationHandler kartuIbuRegistrationHandler;
    private KartuIbuCloseHandler kartuIbuCloseHandler;
    private KartuIbuANCRegistrationHandler kartuIbuANCRegistrationHandler;
    private AnakBayiRegistrationHandler anakBayiRegistrationHandler;
    private KIANCCloseHandler kiancCloseHandler;
    private KIPNCCloseHandler kipncCloseHandler;
    private AnakCloseHandler anakCloseHandler;

    private ANMController anmController;
    private ANMLocationController anmLocationController;

    private KartuIbuRegisterController kartuIbuRegisterController;
    private KartuIbuANCRegisterController kartuIbuANCRegisterController;
    private KartuIbuPNCRegisterController kartuIbuPNCRegisterController;
    private AnakRegisterController anakRegisterController;

    private BidanController bidanController;

    private DristhiConfiguration configuration;

    protected Context() {
    }

    public android.content.Context applicationContext() {
        return applicationContext;
    }


    public static Context getInstance() {
        return context;
    }

    public static Context setInstance(Context context) {
        Context.context = context;
        return context;
    }

    public BeneficiaryService beneficiaryService() {
        if (beneficiaryService == null) {
            beneficiaryService = new BeneficiaryService(allEligibleCouples(), allBeneficiaries());
        }
        return beneficiaryService;
    }

    public Context updateApplicationContext(android.content.Context applicationContext) {
        this.applicationContext = applicationContext;
        return this;
    }

    protected DrishtiService drishtiService() {
        if (drishtiService == null) {
            drishtiService = new DrishtiService(httpAgent(), configuration().dristhiBaseURL());
        }
        return drishtiService;
    }

    public ActionService actionService() {
        if (actionService == null) {
            actionService = new ActionService(drishtiService(), allSettings(), allSharedPreferences(), allReports());
        }
        return actionService;
    }

    public FormSubmissionService formSubmissionService() {
        initRepository();
        if (formSubmissionService == null) {
            formSubmissionService = new FormSubmissionService(ziggyService(), formDataRepository(), allSettings());
        }
        return formSubmissionService;
    }

    public FormSubmissionRouter formSubmissionRouter() {
        initRepository();
        if (formSubmissionRouter == null) {
            formSubmissionRouter = new FormSubmissionRouter(formDataRepository(), ecRegistrationHandler(),
                    fpComplicationsHandler(), fpChangeHandler(), renewFPProductHandler(), ecCloseHandler(),
                    ancRegistrationHandler(), ancRegistrationOAHandler(), ancVisitHandler(), ancCloseHandler(),
                    ttHandler(), ifaHandler(), hbTestHandler(), deliveryOutcomeHandler(), pncRegistrationOAHandler(),
                    pncCloseHandler(), pncVisitHandler(), childImmunizationsHandler(), childRegistrationECHandler(),
                    childRegistrationOAHandler(), childCloseHandler(), childIllnessHandler(), vitaminAHandler(),
                    deliveryPlanHandler(), ecEditHandler(), ancInvestigationsHandler(),
                    kartuIbuRegistrationHandler(), kartuIbuCloseHandler(),
                    kartuIbuANCRegistrationHandler(), anakBayiRegistrationHandler(),kiancCloseHandler(),
                    kipncCloseHandler(), anakCloseHandler());
        }
        return formSubmissionRouter;
    }

    private ChildCloseHandler childCloseHandler() {
        if (childCloseHandler == null) {
            childCloseHandler = new ChildCloseHandler(childService());
        }
        return childCloseHandler;
    }

    private ECRegistrationHandler ecRegistrationHandler() {
        if (ecRegistrationHandler == null) {
            ecRegistrationHandler = new ECRegistrationHandler(eligibleCoupleService());
        }
        return ecRegistrationHandler;
    }

    private FPComplicationsHandler fpComplicationsHandler() {
        if (fpComplicationsHandler == null) {
            fpComplicationsHandler = new FPComplicationsHandler(eligibleCoupleService());
        }
        return fpComplicationsHandler;
    }

    private FPChangeHandler fpChangeHandler() {
        if (fpChangeHandler == null) {
            fpChangeHandler = new FPChangeHandler(eligibleCoupleService());
        }
        return fpChangeHandler;
    }

    private RenewFPProductHandler renewFPProductHandler() {
        if (renewFPProductHandler == null) {
            renewFPProductHandler = new RenewFPProductHandler(eligibleCoupleService());
        }
        return renewFPProductHandler;
    }

    private ECCloseHandler ecCloseHandler() {
        if (ecCloseHandler == null) {
            ecCloseHandler = new ECCloseHandler(eligibleCoupleService());
        }
        return ecCloseHandler;
    }

    private ANCRegistrationHandler ancRegistrationHandler() {
        if (ancRegistrationHandler == null) {
            ancRegistrationHandler = new ANCRegistrationHandler(motherService());
        }
        return ancRegistrationHandler;
    }

    private ANCRegistrationOAHandler ancRegistrationOAHandler() {
        if (ancRegistrationOAHandler == null) {
            ancRegistrationOAHandler = new ANCRegistrationOAHandler(motherService());
        }
        return ancRegistrationOAHandler;
    }

    private ANCVisitHandler ancVisitHandler() {
        if (ancVisitHandler == null) {
            ancVisitHandler = new ANCVisitHandler(motherService());
        }
        return ancVisitHandler;
    }

    private ANCCloseHandler ancCloseHandler() {
        if (ancCloseHandler == null) {
            ancCloseHandler = new ANCCloseHandler(motherService());
        }
        return ancCloseHandler;
    }

    private TTHandler ttHandler() {
        if (ttHandler == null) {
            ttHandler = new TTHandler(motherService());
        }
        return ttHandler;
    }

    private IFAHandler ifaHandler() {
        if (ifaHandler == null) {
            ifaHandler = new IFAHandler(motherService());
        }
        return ifaHandler;
    }

    private HBTestHandler hbTestHandler() {
        if (hbTestHandler == null) {
            hbTestHandler = new HBTestHandler(motherService());
        }
        return hbTestHandler;
    }

    private DeliveryOutcomeHandler deliveryOutcomeHandler() {
        if (deliveryOutcomeHandler == null) {
            deliveryOutcomeHandler = new DeliveryOutcomeHandler(motherService(), childService());
        }
        return deliveryOutcomeHandler;
    }

    private DeliveryPlanHandler deliveryPlanHandler() {
        if (deliveryPlanHandler == null) {
            deliveryPlanHandler = new DeliveryPlanHandler(motherService());
        }
        return deliveryPlanHandler;
    }

    private PNCRegistrationOAHandler pncRegistrationOAHandler() {
        if (pncRegistrationOAHandler == null) {
            pncRegistrationOAHandler = new PNCRegistrationOAHandler(childService());
        }
        return pncRegistrationOAHandler;
    }

    private PNCCloseHandler pncCloseHandler() {
        if (pncCloseHandler == null) {
            pncCloseHandler = new PNCCloseHandler(motherService());
        }
        return pncCloseHandler;
    }

    private PNCVisitHandler pncVisitHandler() {
        if (pncVisitHandler == null) {
            pncVisitHandler = new PNCVisitHandler(motherService(), childService());
        }
        return pncVisitHandler;
    }

    private ChildImmunizationsHandler childImmunizationsHandler() {
        if (childImmunizationsHandler == null) {
            childImmunizationsHandler = new ChildImmunizationsHandler(childService());
        }
        return childImmunizationsHandler;
    }

    private ChildIllnessHandler childIllnessHandler() {
        if (childIllnessHandler == null) {
            childIllnessHandler = new ChildIllnessHandler(childService());
        }
        return childIllnessHandler;
    }

    private VitaminAHandler vitaminAHandler() {
        if (vitaminAHandler == null) {
            vitaminAHandler = new VitaminAHandler(childService());
        }
        return vitaminAHandler;
    }

    private ChildRegistrationECHandler childRegistrationECHandler() {
        if (childRegistrationECHandler == null) {
            childRegistrationECHandler = new ChildRegistrationECHandler(childService());
        }
        return childRegistrationECHandler;
    }

    private ChildRegistrationOAHandler childRegistrationOAHandler() {
        if (childRegistrationOAHandler == null) {
            childRegistrationOAHandler = new ChildRegistrationOAHandler(childService());
        }
        return childRegistrationOAHandler;
    }

    private ECEditHandler ecEditHandler() {
        if (ecEditHandler == null) {
            ecEditHandler = new ECEditHandler();
        }
        return ecEditHandler;
    }

    private ANCInvestigationsHandler ancInvestigationsHandler() {
        if (ancInvestigationsHandler == null) {
            ancInvestigationsHandler = new ANCInvestigationsHandler();
        }
        return ancInvestigationsHandler;
    }

    private ZiggyService ziggyService() {
        initRepository();
        if (ziggyService == null) {
            ziggyService = new ZiggyService(ziggyFileLoader(), formDataRepository(), formSubmissionRouter());
        }
        return ziggyService;
    }

    public ZiggyFileLoader ziggyFileLoader() {
        if (ziggyFileLoader == null) {
            ziggyFileLoader = new ZiggyFileLoader("www/ziggy", "www/form", applicationContext().getAssets());
        }
        return ziggyFileLoader;
    }

    public FormSubmissionSyncService formSubmissionSyncService() {
        if (formSubmissionSyncService == null) {
            formSubmissionSyncService = new FormSubmissionSyncService(formSubmissionService(), httpAgent(), formDataRepository(), allSettings(), allSharedPreferences(), configuration());
        }
        return formSubmissionSyncService;
    }

    private HTTPAgent httpAgent() {
        if (httpAgent == null) {
            httpAgent = new HTTPAgent(applicationContext, allSettings(), allSharedPreferences(), configuration());
        }
        return httpAgent;
    }

    private Repository initRepository() {
        if (repository == null) {
            if(this.configuration().getAppName().equals("BIDAN")) {
                repository = initBidanRepository();
            } else {
                repository = initDefaultRepository();
            }
        }
        return repository;
    }

    private Repository initBidanRepository() {
        return new Repository(this.applicationContext, session(), settingsRepository(),
                alertRepository(), timelineEventRepository(), reportRepository(),
                formDataRepository(), serviceProvidedRepository(), kartuIbuRepository(), ibuRepository(),
                anakRepository());
    }

    private Repository initDefaultRepository() {
        return new Repository(this.applicationContext, session(), settingsRepository(), alertRepository(),
                eligibleCoupleRepository(), childRepository(), timelineEventRepository(), motherRepository(), reportRepository(),
                formDataRepository(), serviceProvidedRepository());
    }

    public AllEligibleCouples allEligibleCouples() {
        initRepository();
        if (allEligibleCouples == null) {
            allEligibleCouples = new AllEligibleCouples(eligibleCoupleRepository(), alertRepository(), timelineEventRepository());
        }
        return allEligibleCouples;
    }

    public AllAlerts allAlerts() {
        initRepository();
        if (allAlerts == null) {
            allAlerts = new AllAlerts(alertRepository());
        }
        return allAlerts;
    }

    public AllSettings allSettings() {
        initRepository();
        if (allSettings == null) {
            allSettings = new AllSettings(allSharedPreferences(), settingsRepository());
        }
        return allSettings;
    }

    public AllSharedPreferences allSharedPreferences() {
        if (allSharedPreferences == null) {
            allSharedPreferences = new AllSharedPreferences(getDefaultSharedPreferences(this.applicationContext));
        }
        return allSharedPreferences;
    }

    public AllBeneficiaries allBeneficiaries() {
        initRepository();
        if (allBeneficiaries == null) {
            allBeneficiaries = new AllBeneficiaries(motherRepository(), childRepository(), alertRepository(), timelineEventRepository());
        }
        return allBeneficiaries;
    }

    public AllTimelineEvents allTimelineEvents() {
        initRepository();
        if (allTimelineEvents == null) {
            allTimelineEvents = new AllTimelineEvents(timelineEventRepository());
        }
        return allTimelineEvents;
    }

    public AllReports allReports() {
        initRepository();
        if (allReports == null) {
            allReports = new AllReports(reportRepository());
        }
        return allReports;
    }

    public AllServicesProvided allServicesProvided() {
        initRepository();
        if (allServicesProvided == null) {
            allServicesProvided = new AllServicesProvided(serviceProvidedRepository());
        }
        return allServicesProvided;
    }

    private EligibleCoupleRepository eligibleCoupleRepository() {
        if (eligibleCoupleRepository == null) {
            eligibleCoupleRepository = new EligibleCoupleRepository();
        }
        return eligibleCoupleRepository;
    }

    private AlertRepository alertRepository() {
        if (alertRepository == null) {
            alertRepository = new AlertRepository();
        }
        return alertRepository;
    }

    private SettingsRepository settingsRepository() {
        if (settingsRepository == null) {
            settingsRepository = new SettingsRepository();
        }
        return settingsRepository;
    }

    private ChildRepository childRepository() {
        if (childRepository == null) {
            childRepository = new ChildRepository();
        }
        return childRepository;
    }

    private MotherRepository motherRepository() {
        if (motherRepository == null) {
            motherRepository = new MotherRepository();
        }
        return motherRepository;
    }

    private TimelineEventRepository timelineEventRepository() {
        if (timelineEventRepository == null) {
            timelineEventRepository = new TimelineEventRepository();
        }
        return timelineEventRepository;
    }

    private ReportRepository reportRepository() {
        if (reportRepository == null) {
            reportRepository = new ReportRepository();
        }
        return reportRepository;
    }

    public FormDataRepository formDataRepository() {
        if (formDataRepository == null) {
            formDataRepository = new FormDataRepository();
        }
        return formDataRepository;
    }

    private ServiceProvidedRepository serviceProvidedRepository() {
        if (serviceProvidedRepository == null) {
            serviceProvidedRepository = new ServiceProvidedRepository();
        }
        return serviceProvidedRepository;
    }

    public UserService userService() {
        if (userService == null) {
            Repository repo = initRepository();
            userService = new UserService(repo, allSettings(), allSharedPreferences(), httpAgent(), session(), configuration(), saveANMLocationTask());
        }
        return userService;
    }

    private SaveANMLocationTask saveANMLocationTask() {
        if (saveANMLocationTask == null) {
            saveANMLocationTask = new SaveANMLocationTask(allSettings());
        }
        return saveANMLocationTask;
    }

    public AlertService alertService() {
        if (alertService == null) {
            alertService = new AlertService(alertRepository());
        }
        return alertService;
    }

    public ServiceProvidedService serviceProvidedService() {
        if (serviceProvidedService == null) {
            serviceProvidedService = new ServiceProvidedService(allServicesProvided());
        }
        return serviceProvidedService;
    }

    public EligibleCoupleService eligibleCoupleService() {
        if (eligibleCoupleService == null) {
            eligibleCoupleService = new EligibleCoupleService(allEligibleCouples(), allTimelineEvents(), allBeneficiaries());
        }
        return eligibleCoupleService;
    }

    public MotherService motherService() {
        if (motherService == null) {
            motherService = new MotherService(allBeneficiaries(), allEligibleCouples(), allTimelineEvents(), serviceProvidedService());
        }
        return motherService;
    }

    public ChildService childService() {
        if (childService == null) {
            childService = new ChildService(allBeneficiaries(), motherRepository(), childRepository(), allTimelineEvents(), serviceProvidedService(), allAlerts());
        }
        return childService;
    }

    public Session session() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public ANMService anmService() {
        if (anmService == null) {
            anmService = new ANMService(allSharedPreferences(), allBeneficiaries(), allEligibleCouples());
        }
        return anmService;
    }

    public Cache<String> listCache() {
        if (listCache == null) {
            listCache = new Cache<String>();
        }
        return listCache;
    }

    public Cache<SmartRegisterClients> smartRegisterClientsCache() {
        if (smartRegisterClientsCache == null) {
            smartRegisterClientsCache = new Cache<SmartRegisterClients>();
        }
        return smartRegisterClientsCache;
    }

    public Cache<HomeContext> homeContextCache() {
        if (homeContextCache == null) {
            homeContextCache = new Cache<HomeContext>();
        }
        return homeContextCache;
    }

    public Boolean IsUserLoggedOut() {
        return userService().hasSessionExpired();
    }

    public DristhiConfiguration configuration() {
        if (configuration == null) {
            configuration = new DristhiConfiguration(getInstance().applicationContext().getAssets());
        }
        return configuration;
    }

    public PendingFormSubmissionService pendingFormSubmissionService() {
        if (pendingFormSubmissionService == null) {
            pendingFormSubmissionService = new PendingFormSubmissionService(formDataRepository());
        }
        return pendingFormSubmissionService;
    }

    public ANMController anmController() {
        if (anmController == null) {
            anmController = new ANMController(anmService(), listCache(), homeContextCache());
        }
        return anmController;
    }

    public ANMLocationController anmLocationController() {
        if (anmLocationController == null) {
            anmLocationController = new ANMLocationController(allSettings(), listCache());
        }
        return anmLocationController;
    }

    public KartuIbuRegisterController kartuIbuRegisterController() {
        if (kartuIbuRegisterController == null) {
            kartuIbuRegisterController = new KartuIbuRegisterController(allKartuIbus(), listCache(), kiClientsCache(), allKohort());
        }
        return kartuIbuRegisterController;
    }

    public KartuIbuANCRegisterController kartuIbuANCRegisterController() {
        if (kartuIbuANCRegisterController == null) {
            kartuIbuANCRegisterController = new KartuIbuANCRegisterController(allKohort(), listCache(), kartuIbuANCClientsCache(), villagesCache());
        }
        return kartuIbuANCRegisterController;
    }

    public AnakRegisterController anakRegisterController() {
        if (anakRegisterController == null) {
            anakRegisterController = new AnakRegisterController(allKohort(), alertService(), serviceProvidedService(), listCache(), smartRegisterClientsCache(), villagesCache());
        }
        return anakRegisterController;
    }

    public KartuIbuPNCRegisterController kartuIbuPNCRegisterController() {
        if (kartuIbuPNCRegisterController == null) {
            kartuIbuPNCRegisterController = new KartuIbuPNCRegisterController(allKohort(), listCache(), kartuIbuPNCClientsCache(), villagesCache());
        }
        return kartuIbuPNCRegisterController;
    }

    //#TODO: Refactor to use one cache object
    public Cache<ECClients> ecClientsCache() {
        if (ecClientsCache == null) {
            ecClientsCache = new Cache<ECClients>();
        }
        return ecClientsCache;

    }

    //#TODO: Refactor to use one cache object
    public Cache<FPClients> fpClientsCache() {
        if (fpClientsCache == null) {
            fpClientsCache = new Cache<FPClients>();
        }
        return fpClientsCache;

    }

    //#TODO: Refactor to use one cache object

    public Cache<ANCClients> ancClientsCache() {
        if (ancClientsCache == null) {
            ancClientsCache = new Cache<ANCClients>();
        }
        return ancClientsCache;
    }

    public Cache<PNCClients> pncClientsCache() {
        if (pncClientsCache == null) {
            pncClientsCache = new Cache<PNCClients>();
        }
        return pncClientsCache;
    }

    public Cache<Villages> villagesCache() {
        if (villagesCache == null) {
            villagesCache = new Cache<Villages>();
        }
        return villagesCache;
    }

    public Cache<Typeface> typefaceCache() {
        if (typefaceCache == null) {
            typefaceCache = new Cache<Typeface>();
        }
        return typefaceCache;
    }

    public String getStringResource(int id) {
        return applicationContext().getResources().getString(id);
    }

    public int getColorResource(int id) {
        return applicationContext().getResources().getColor(id);
    }

    public Drawable getDrawable(int id) {
        return applicationContext().getResources().getDrawable(id);
    }

    public Drawable getDrawableResource(int id) {
        return applicationContext().getResources().getDrawable(id);
    }

    // Kartu Ibu Functions
    private KartuIbuRegistrationHandler kartuIbuRegistrationHandler() {
        if (kartuIbuRegistrationHandler == null) {
            kartuIbuRegistrationHandler = new KartuIbuRegistrationHandler(kartuIbuService());
        }
        return kartuIbuRegistrationHandler;
    }

    private KartuIbuCloseHandler kartuIbuCloseHandler() {
        if (kartuIbuCloseHandler == null) {
            kartuIbuCloseHandler = new KartuIbuCloseHandler(kartuIbuService());
        }
        return kartuIbuCloseHandler;
    }

    private KartuIbuService kartuIbuService() {
        if(kartuIbuService == null) {
            kartuIbuService = new KartuIbuService(allKartuIbus(), allTimelineEvents(), allKohort());
        }
        return kartuIbuService;
    }

    public AllKartuIbus allKartuIbus() {
        initRepository();
        if (allKartuIbus == null) {
            allKartuIbus = new AllKartuIbus(kartuIbuRepository(), alertRepository(),
                    timelineEventRepository(), configuration());
        }
        return allKartuIbus;
    }

    private KartuIbuRepository kartuIbuRepository() {
        if (kartuIbuRepository == null) {
            kartuIbuRepository = new KartuIbuRepository();
        }
        return kartuIbuRepository;
    }

    public Cache<KartuIbuClients> kiClientsCache() {
        if (kartuIbuClientsCache == null) {
            kartuIbuClientsCache = new Cache<KartuIbuClients>();
        }
        return kartuIbuClientsCache;

    }

    public Cache<KBClients> kbClientsCache() {
        if (kbClientsCache == null) {
            kbClientsCache = new Cache<KBClients>();
        }
        return kbClientsCache;
    }

    private IbuRepository ibuRepository() {
        if(ibuRepository == null) {
            ibuRepository = new IbuRepository();
        }
        return ibuRepository;
    }

    private AnakRepository anakRepository() {
        if(anakRepository == null) {
            anakRepository = new AnakRepository();
        }
        return anakRepository;
    }

    private IbuService ibuService() {
        if(ibuService == null) {
            ibuService = new IbuService(allKartuIbus(), allTimelineEvents(), serviceProvidedService(), allKohort());
        }
        return ibuService;
    }

    private AnakService anakService() {
        if(anakService == null) {
            anakService = new AnakService(allKohort(), ibuRepository(), anakRepository(),
                    allTimelineEvents(), serviceProvidedService(), allAlerts());
        }
        return anakService;
    }

    public AllKohort allKohort() {
        if(allKohort == null) {
            allKohort = new AllKohort(ibuRepository(), anakRepository(), alertRepository(), timelineEventRepository(), configuration());
        }
        return allKohort;
    }

    public Cache<KartuIbuANCClients> kartuIbuANCClientsCache() {
        if(kartuIbuANCClientsCache == null){
            kartuIbuANCClientsCache = new Cache<KartuIbuANCClients>();
        }
        return kartuIbuANCClientsCache;
    }

    public Cache<KartuIbuPNCClients> kartuIbuPNCClientsCache() {
        if(kartuIbuPNCClientsCache == null){
            kartuIbuPNCClientsCache = new Cache<KartuIbuPNCClients>();
        }
        return kartuIbuPNCClientsCache;
    }

    public Cache<BidanHomeContext> bidanHomeContextCache() {
        if (bidanHomeContextCache == null) {
            bidanHomeContextCache = new Cache<BidanHomeContext>();
        }
        return bidanHomeContextCache;
    }

    public BidanController bidanController() {
        if (bidanController == null) {
            bidanController = new BidanController(bidanService(), listCache(), bidanHomeContextCache());
        }
        return bidanController;
    }

    public BidanService bidanService() {
        if(bidanService == null) {
            bidanService = new BidanService(allSharedPreferences(), allKartuIbus(), allKohort());
        }
        return bidanService;
    }

    public KartuIbuANCRegistrationHandler kartuIbuANCRegistrationHandler() {
        if(kartuIbuANCRegistrationHandler == null) {
            kartuIbuANCRegistrationHandler = new KartuIbuANCRegistrationHandler(ibuService());
        }
        return kartuIbuANCRegistrationHandler;
    }

    public AnakBayiRegistrationHandler anakBayiRegistrationHandler() {
        if(anakBayiRegistrationHandler == null) {
            anakBayiRegistrationHandler = new AnakBayiRegistrationHandler(anakService());
        }
        return anakBayiRegistrationHandler;
    }

    public KIANCCloseHandler kiancCloseHandler() {
        if(kiancCloseHandler == null) {
            kiancCloseHandler = new KIANCCloseHandler(ibuService());
        }
        return kiancCloseHandler;
    }

    public KIPNCCloseHandler kipncCloseHandler() {
        if(kipncCloseHandler == null) {
            kipncCloseHandler = new KIPNCCloseHandler(ibuService());
        }
        return kipncCloseHandler;
    }

    public AnakCloseHandler anakCloseHandler() {
        if(anakCloseHandler == null) {
            anakCloseHandler = new AnakCloseHandler(anakService());
        }
        return anakCloseHandler;
    }

}
