# Naloga1

Z integriranim razvojnim okoljem IntelliJ IDEA in generatorjem vmesnikov Scene Builder realizirajte grafični uporabniški vmesnik v grafičnem okolju JavaFX, ki temelji na glavnem oknu (Stage). Glavno okno vsebuje naslednje komponente:

-vrstični meni (MenuBar) z možnostmi Datoteka, Uredi, Pogled in Pomoč (ki so kaskadni gumbi, katerim sledijo izvlečni meniji (Menu)),  
-orodno vrstico (ToolBar),  
-delovno področje glavnega okna, ki je nek vsebovalnik, lahko je sidrni vsebovalnik (AnchorPane), ki vsebuje več podob, naštetih spodaj, in  
-statusno vrstico (Label).

Delovno področje glavnega okna vsebuje naslednje podobe:

-tri polja za vnos besedila (TextField), skupaj s pojasnilnim besedilom (Label)  
-tri izključujoča stikala (RadioButton)  
-ukazni gumb (Button),  
-kombiniran izvlečni seznam (ComboBox),  
-krožno polje (Spinner),  
-stikalo (CheckBox),   
-besedilno območje (TextArea) in  
-vrstico za sporočila (Label).  

Podčrtane črke v imenih možnosti so mnemoniki, ki jih morate tudi realizirati.  

Meni Datoteka naj vsebuje elemente (MenuItem) Odpri (klic dialoga za izbiro datoteke (FileChooser)), Pobriši (pobriše vsebino vrstice za sporočila in statusne vrstice) in Izhod (konča program). Vsem opcijam menija Datoteka dodajte tudi ustrezne bližnjice (npr. Ctrl+O, Ctrl+C in Ctrl+Q). Meni Uredi naj vsebuje končne izbire Ime, Priimek in Drzava, ki so menijska stikala (CheckMenuItem).  Meni Pogled pa naj vsebuje končno izbiro Izpisi vse. Implementirajte tudi orodno vrstico (ToolBar), ki naj vsebuje vse končne izbire iz menijev Datoteka in Pogled. Vmesnik naj vsebuje tudi statusno vrstico, ki naj bo oznaka (Label), v katero se naj ob izbiri možnosti iz menijev Uredi in Pogled (ali iz orodne vrstice) zapiše opis izbrane opcije.

V delovnem področju glavnega okna implementirajte tri polja za vnos besedila (TextField), kjer je vsako namenjeno vnosu imena, priimka oziroma države, skupaj z oznako (Label), ki pojasnjuje to polje. Polja naj bodo na začetku onemogočena. Posamezno izmed polj pa omogočimo z izbiro (postavitvijo) ustreznega stikala v meniju Uredi. V delovno področje dodajte tudi kombiniran izvlečni seznam (ComboBox), ki naj v eni od svojih končnih izbir vsebuje vaše ime, priimek in državo, ločeno z vejicami (npr. Janko, Bananko, Bananastan). Dodajte tudi izključujoča stikala (RadioButton) "Dodaj", "Odstrani prvega" in "Odstrani izbranega", ukazni gumb (Button) "Izvedi akcijo", krožno polje (Spinner), stikalo (CheckBox) "Dvojniki dovoljeni" in vrstico za sporočila (Label). Ob pritisku na gumb "Izvedi akcijo" naj se izvede akcije glede na izbrano izključujoče stikalo. V primeru da je izbrano izključujoče stikalo "Dodaj", naj se vsebina polj za vnos imena, priimka in države združi v niz, kjer so posamezne vrednosti ločene z vejico (glej primer za začetno vsebino kombiniranega izvlečnega seznama), nato pa naj se ta niz doda kot nov element v kombiniran izvlečni seznam če 1) so vsa polja za vnos omogočena in niso prazna ter 2) če še ne obstaja zapis s tako vsebino v kombiniranem izvlečnem seznamu ali pa so dvojniki dovoljeni. Pritisk na gumb "Izvedi akcijo" naj ob izbranem izključujočem stikalu "Odstrani prvega" oziroma "Odstrani izbranega" povzroči odstranitev prvega elementa oziroma izbranega elementa (to je element, ki je v tem trenutku prikazan v kombiniranem izvlečnem seznamu) iz kombiniranega izvlečnega seznama, če ta ni prazen. V vrstico za sporočila naj se izpisuje, katera operacija se trenutno izvaja, npr. "Dodajam", "Odstranjujem prvega", "Odstranjujem izbranega". S stikalom (CheckBox) "Dvojniki dovoljeni" pa nadziramo dodajanje podvojenih elementov v kombiniran izvlečni seznam. Če je to stikalo izbrano, je dodajanje elementov, ki so že v seznamu, dovoljeno, sicer pa je prepovedano. Krožno polje naj hrani cela števila med 0 in 20. Ob spremembi izbrane vrednosti v krožnem polju naj se v vrstico za sporočila izpiše element iz kombiniranega izvlečnega seznama, ki se nahaja na izbranem položaju, oziroma "Ni elementa", če v seznamu ni toliko elementov. V vmesnik pa dodajte tudi besedilno območje, v katerega se ob pritisku na končno izbiro Izpisi vse menija Pogled izpiše vsebina kombiniranega izvlečnega seznama (vsak vnos v svojo vrstico). Za prikaz in grupiranje vseh teh komponent uporabite nek vsebovalnik (npr. AnchorPane, BorderPane, GridPane, ...).

![Uporabniški vmesnik programa](https://github.com/klemenp950/UV/blob/main/Naloga1/Slike/slika.png)
