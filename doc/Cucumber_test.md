# Cucumber tesztek (BDD tesztek)

BDD tesztek írásához Cucumber keretrendszert használtuk. 

Összsesen 6 scenario-t írtunk, amiből kettőnél több, különböző tesztértékeket is megadtunk. Így összesen 11 teszt futott le. A tesztek 3 különböző feature-t fedtek le.
## Tesztesetek:
- Az első azt teszteli, hogy a converter csak 3 bemeneti argumentummal fusson le hiba nélkül: `are_there_enough_arguments.feature`
  - Először bemeneti argumentumok számát 3-nak adtuk meg.
    - Eredmény: hiba nélkül lefutott
  - Ezután több különböző értékkel is kipróbáltuk (2, 1, 0, és 4)
    - Eredmény: mindegyik esetnél IllegalArgumentException hibát dobott
- A második tesztesetnél azt viszgáltuk meg, hogy a BNode generator csak akkor adjon vissza nem null objektumot, ha a kapott rowIndex argumentuma nem -1, tehát valid érték: `bNodeGenerate.feature`
  - Először több különböző értéket adtuk meg a rowIndex-nek (1, 3 ,6)
    - Eredmény: Létrehozott BNode objektum ami nem null.
  - Utána a rowIndex-et az alábbi értékre állítottuk be: -1
    - Eredmény: a kapott érték null
- A harmadik tesztesetnél azt vizsgáltuk meg, hogy az oszlop index értékét visszaadó függvény akkor is jól működjön, ha számot (int) adunk meg és akkor is ha oszlopot indexelő karaktert: `bNodeGenerate.feature`
  - Először egy olyan stringet adtunk meg amiben egy szám van ("10")
    - Eredmény: a kapott index 10
  - Ezután egy másik sztringet adtunk meg: "M";
    - A várt eredmény ekkor 12, mert az angol abc-ben ez a 13. és 0-tol indexelünk.
    - Eredmény: 12, tehát a funkció jól működik.

### Tapasztalatok:
A második teszteset először hibásan futott le, mert a kódban egy if kifejezés hibásan volt megadva. Erre kiírtunk egy issue-t: `BNodeGenerator.generate function is wrong`. A hiba kijavítása után a teszt is rendesen lefutott.

További tapasztalat még, hogy a dependencies-nel fontos, hogy minden cucumber dependency-nek ugyanaz legyen a verziószáma. Illetve fontos megjegyezni, hogy IDE beállítás függő lehet az, hogy a dependency hozzáadása után automatikusan újra indexelődik-e a maven projekt.

Ezen kívül fontos, hogy dependency-be hozzáadjuk a cucumber-testng-t és helyesen konfiguráljuk, mert anélkül nem fogja futtatni a maven test a cucumberes teszteket.