Diaconescu Oana 323CD

Inainte de inceperea jocului, se aloca un obiect de tip PlayerTurns care
contine o lista ordonata a obiectelor jucator. Exista 3 obiecte corespunzatoare
celor 3 tipuri de jucatori: Basic, Bribe si Greedy, care extind clasa
abstracta Player.

Metodele comune jucatorilor sunt implementate in clasa Player:
* setPlayer() - initializeaza campurile jucatorului care se modifica la
                fiecare subrunda: goodsInHand si roundNumber
* earnGoods() - transfera bunurile din mana unui jucator (hashmap-ul goodsInHand)
                pe taraba acestuia (goodsOnTable)

In plus, fiecare jucator are o implementare proprie a urmatoarelor metode
abstracte:
* fillBag()        - strategia de comerciant a jucatorului
* pickCriminals()  - strategia de serif a jucatorului


Jocul incepe prin insatantierea unui obiect de tip Game si apelarea metodei
playGame() a acestuia. In obiectul de tip Game "se joaca" toate rundele si se
actualizeaza roundNumber din celelalte clase.

Obiectul de tip Game instantiaza un obiect de tip Round si unul de tip
Subround. In cadrul obiectului de tip Round "se joaca" toate subrundele si se
stabileste cine este seriful. In cadrul obiectului de tip "Subround" se apeleaza
3 metode corespunzatoare celor 3 etape ale unei subrunde:

1. goToInspection()  - Fiecare jucator primeste 10 carti si isi reinitializeaza
                       sacul, dupa care se aplica strategia de umplere a sacului
                       corespunzatoare fiecarui tip de jucator
2. sheriffControl()  - Seriful controleaza comerciantii in functie de regulile
                       tipului sau
3. reachNottingham() - Comerciantii isi verifica sacul, iar bunurile ramase in
                       urma inspectiei sunt adaugate pe taraba

In implementarea celor 3 etape se folosesc urmatoarele clase utilitare:

ETAPA 1:
* Bag          -  contine un hashmap cu bunurile din sac, precum si campuri in
                  care se retine bunul declarat, numarul de bunuri din sac si
                  mita adaugata; prelucrarile facute asupra sacului ('adaugare
                  carte' si 'golire sac') sunt implementate aici
* BagFiller    -  contine metode ce implementeaza strategia efectiva de adaugare
                  a bunurilor in sac de catre fiecare jucator; referinta la aceasta
                  clasa este salvata in Bag, astfel incat fiecare jucator are acces
                  la toate metodele sale; in acest mod, jucatori cu strategii diferite
                  au acces la aceeasi functie (de ex: greedy care foloseste strategia
                  base)
* FilterGoods  -  transforma lista de bunuri din mana intr-un hashmap si le imparte
                  in carti legale si ilegale
* OrderGoods   -  ordoneaza bunurile in functie de cele 2 tipuri de criterii:
                  Frecventa-Profit-Id sau Profit-Id; se folosesc aceleasi metode
                  pentru bunurile legale si pentru cele ilegale

ETAPA 2:
*BagChecker   - nu implementeaza strategiile fiecarui jucator (precum BagFiller),
                 ci implementeaza metoda comuna acestora: inspectia; astfel,
                 alegerea 'pe cine inspectez?' a serifului este facuta in
                 clasa de tip Player; clasa bagChecker preia doar o referinta la
                 comerciant si una la serif si inspecteaza comerciantul, adaugand
                 sau scazand penalty-uri
ETAPA 3:

In aceasta etapa, sacii sunt deja inspectati, bunurile ilegale sunt confiscate,
iar orice transfer monetar(mita sau penalty) este terminat. Astfel, tot ce ramane
de facut este adaugare bunurilor pe taraba, pentru care se apeleaza Player.earnGoods().

Clasele ProfitCalculator si Rank incheie jocul, cacluland punctajele finale si
afisand clasamentul.
