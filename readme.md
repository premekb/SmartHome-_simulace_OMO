# Smart home
## CMDline argumenty
Pokud nejsou žádné specifikovány, tak se zapne simulace s defaultní konfigurací. Pro spuštění s jinou konfigurací je potřeba spustit simulaci s cmdline argumentem obsahující jméno souboru (např. "Configuration2.json").
## Diagramy
Ve složce Navrh. Neodpovídá úplně one to one výsledné implementaci, ale zásadním způsobem jsme se od něj nijak neodklonili.
## Krátký popis průběhu simulace (Simulation.execute())
Při spuštění se podle konfigurace inicialisuje dům pomocí factories a builderu. Potom se v každé iteraci vrátí všichni obyvatelé z OutsideWorld do House a na všech devicech a obyvatelích se zavolá **.simulateOneTick()**, kde se potom náhodně vyhodnotí, jestli se vygeneruje nějaký náhodný event, jestli osoba chce jít ven, anebo interagovat se zařízeními, jaké to budou interakce, započítá se spotřeba, opotřebení atd... . Následně se projdou všechny vygenerované eventy **(house.handleEvents())**, a jsou o nich notifikování vhodní odběratelé.
## Popis realisace jednotlivých funkčních požadavků
V závorkách po funkčním požadavku je výčet hlavních tříd/packages/metod, které souvisí s daným FR. Splněny jsou všechny požadavky.

- **F1 - výčet entit se kterýma se pracuje:** Entity se nacházi v programu pod stejnými jmény.
- **F2 - Stav a API zařízení (DeviceState, Person.use(Device device), HasCook, Temperature, Food, CD...):** Všechna zařízení mají DeviceState, podle kterého mají spotřebu a API pro měnení tohoto stavu. Se zařízeními může Person interagovat pomocí metody use. Vybrané zařízeni maji Traity, které rozšiřují jejich funkcionalitu. Např. Oven a Microvave maji trait HasCook, který umožňuje vařit jídlo, anebo AC má trait Temperature. Některá zařízení také obsahují nějaký Item, např. Fridge může obsahovat Food, anebo AudioVideoReceiver může obsahovat CD.
- **F3 - Spotřeba zařízení a jejich stav: (DeviceConsumptionTracker, DeviceConsumption, DeviceConsumptionRate)**
- **F4 - API pro sběr dat o spotřebě (DeviceConsumptionTracker, DeviceConsumption, DeviceConsumptionRate):** Kazdý Device obsahuje DeviceTracker, který poskytuje API pro sběr spotřeby. Lze ho vyresetovat a měřit tak spotřebu v růžných časových intervalech.
- **F5 - Akce obyvatel na ostatní zařízení a obyvatele (Person.use(Device), package events)**: Se zařízeními mohou Person náhodně interagovat pomocí metody use. Skrz event systém mohou interagovat jak se zařízeními tak i s ostatními obyvateli (např. Device vyhodí IsBroken -> Je náhodně vybrán obyvatel, který poslouchá tento Event a je doma -> přijde a Device opraví). 
- **F6, F7 Event systém (EventPublisher, EventConsumer, Observer, Observable, package events, House)**
   - EventPublisher a EventConsumer – Device a Inhabitant jsou EventPublisher a notifikují House, který je EventConsumer o vzniku Eventu.
   - Observer a Observable – Device a Inhabitant jsou Observer a jsou notifikování domem o vzniku Eventu, jehož typ odebírají. Notifikování odběratel o Eventu se vždy děje na konci časové jednotky simulace. Osoby mohou odbavit jakýkoliv Event na jehož odběr se přihlásili, protože se mohou hýbat. Devices odbavují vždy pouze Eventy, které vznikly v místnosti, ve které se nacházejí, protože se nemohou přemístit do jiné mistnosti. O některých událostech je nutné notifikovat všechny relevantní odběratele (např. v místnosti je tma, rozsvítí se tam všechny světla), o některých Eventech je notifikován náhodně vždy jenom jeden odběratel. Např. Kid IsCrying, přijde jenom jeden Adult.
   - Náhodně generované eventy: EventPublisher interface obsahuje metody, pomocí kterých lze určit jaké Eventy bude ten kdo Interface implementuje generovat. 
- **F8 Reporty (House, HasReport, package reports/visitors)** - House vystavuje API pro získaní reportů. Např. getEventReport(). Tvorba těchto reportů je řešena skrz jednotlivé visitory (package reports/visitors)
- **F9 Rozbité zařízení (Manual, Warranty, Device.repair(), Adult.notify(IsBroken event))** - Každému zařízení je náhodně přidělena nějaký durability, která se v každé iteraci simulace snižuje o konstantní jednotku danou v konfiguraci. Jakmile tato durability klesne pod nulu, tak se zařízení vypne a při interakci zůstavá pořád vypnuté, dokuď někdo neodbaví Event IsBroken a v tomto "odbavování" nezavolá metodu repair na zařízení, která vyžaduje jako argumenty Manual a Warranty.
- **F10 Chození ven (OutsideWorld, Person.goSport(), sportsEquipment, SportsEquipmentRack)** - Na začátku jednotky simulace, se vždy všechny osoby vrátí zpátky domů a pak se náhodně rozhodnou, jestli chcou jít ven, anebo ne. V případě, že uz pro ně nezbývá žádné sprotovní náčiní tak provádějí akce v domě. Na začátku dalšího cyklu se pak pokusí jít ven znovu. Když jsou osoby venku, tak neodbavují ani negenerují Eventy.


## Použité design patterny (A třídy kde je lze najít):
- State machine - DeviceState, CookState
- Iterator – SmartHomeIterator, RoomIterator
- Factory/Factory method – AbstractHouseFactory, OrdinaryHouseFactory, LuxuriousHouseFactory, RoomFactory (Používá se pro inicialisaci domu)
- Builder – FloorBuilder, RoomBuilder (Používá se pro inicialisaci domu)
- Singleton – House, OutsideWorld, Configuration (Singletony nejsou thread-safe, protože v zadání je napsáno, že aplikace má běžet pouze v jednom vlákně.)
- Visitor - reports/visitors, Eventy (např. IsBroken)
- Observer/Event listener - EventConsumer, EventPublisher, Observer, Observable
- Object Pool – ManualPool, předpokládáme, že Manual je pro každý druh zařízení vždycky stejný, takže pro každou třídu zařízení se v případě nutnosti vytvoří vždy pouze jenom jeden manuál.
- Lazy Initialization – Device.getWarranty()
- Immutable objekty - DeviceConsumption, DeviceConsumptionRate
- Traits - HasCook, Brightness. Snažili jsme se lehce inspirovat tím jak je řešeno API od google smart home. (https://developers.google.com/assistant/smarthome/traits)

## Dodatek
### Generický iterátor
V průběhu implementace semestrálky jsme potřebovali procházet všechny druhy objektů, které se mohou vyskytovat v nějaké místnosti. Zprvu jsme implementovali iterátor pro každý takový objekt zvlášť, ale později jsme zjistili, že logika pro procházení je vlastně pokaždé stejná, takže jsme se iterátor pro objekty, které jsou přímo v místnosti rozhodli udělat generický (**třída SmartHomeIterator**). 

### Immutable spotřeba zařízení
Spotřebu zařízení trackuje třída **DeviceTracker**, kde jsme se snažili inspirovat jedním z prvních cvik, kde jsme psali tracker na měření ujetých kilometrů. Samotnou spotřebu jsme se pak rozhodli implementovat pomocí immutable objektů (třída **DeviceConsumption**), aby se nemohlo stát, že spotřeba na zařízení bude nějakou chybou nedopatřením změnena někde jinde v kódu. Její API jsme se rozhodli udělat po vzoru jiných immutable tříd v Jave (např. Money.of()).

### Event systém
Nakonec si myslíme, že se nám celkem povedl event systém, který umožňuje:
- jednoduše přidávat nové druhy eventů
- registrovat, odregistroavt posluchače na různé druhy eventů
- určit, jestli musí být event odbaven všemi posluchači, anebo pouze jedním
- nechat entitu náhodně generovat zvolené eventy
- zajistit, že pokud se entita může hýbat (např. Person), tak může odbavit i Event v jiné místnosti, pokud se ale hýbat nemůže (např. Light), tak nemůže odbavit Event, že je tma v jiné místnosti

