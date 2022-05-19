# SonarCloud beüzemelése

A SonarCloud beüzemelésénél probléma volt, hogy nem volt jogosultságunk a repo-nk-ra beállítani a SonarCloud-ot, de miután kaptunk admin jogokat a saját tárhelyünkre, utána már zökkenőmentesen lehetett folytatni a beüzemelést.

A SonarCloud-ot úgy állítottuk be, hogy ez is fusson le minden egyes push-ra és pull request-re, ami a `main` vagy a `dev` ágon történik, ezzel elérve, hogy ellenőrizhessük a kód minőségét biztonsági szempontból és teszt lefedettség szempontjából is.

## Tapasztalatok a SonarCloud futása során

A projekt használ egy elavult hashelő algoritmust, de ez nem probléma, mert nem jelszó, vagy hasonló jellegű biztonsági célra használja.
A másik probléma pedig a `printStackTrace()` használata volt, de mivel nem éles környezetben fut a kód, így megállapítottuk, hogy ez sem jelent biztonsági kockázatot.