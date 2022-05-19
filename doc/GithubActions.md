# GitHub Actions beüzemelése

A projekt eredetileg `Ant`-et használt buildelésre. Ezt átírtuk Maven-re, és lett egy `pom` file-unk, amit fel lehetett használni a GitHub Actions konfigurálása során.

A fejlesztés során úgy dolgoztunk, hogy a `main` ágból leágaztunk még egy köztes `dev` ágat, amit a fejlesztésre használtunk, ebből ágaztunk le az egyes `issuek` során. Emiatt a GitHub Actions-t úgy konfiguráltuk, hogy a `main` és a `dev` ágagkra történő pull requestek és push-ok során is lefusson az ellenőrzés.

## Tapasztalatok a GitHub Actions beüzemelése és futtatása során
Érdemes bekapcsolni a `--no-transfer-progress` opciót a buildelés során, mert sokkal olvashatóbb lesz a kimenet, hiszen nem lesz "tele szemetelve" letöltés logokkal.

Érdemes bekapcsolni a cachelést a Maven packages-re, mert meggyorsítja a workflow futását, mert egyrészt limitált az ingyenesen futtatható percek száma, másrészt gyorsabb visszajelzést kapunk az eredményről.