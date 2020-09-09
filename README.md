# moviedb-viewer
Android app for movie db API 

Komentarz do zadania:

Z uwagi na ograniczony czas postanowiłem nieco uprościć zadanie, starąjąc się zachować praktyki dobrego programowania oraz wymagania. Jako propozycje do zmiany i rozbudowy aplikacji wskazałbym:
1. Feature dynamic module, zamisat fragmentów w module App
2. Przygotowanie różnych flavorów tak aby umożliwić mockowanie implementacji. Można użyć tego do napisania testów espresso. 
3. Dopisanie testów do repozytoriów, mapperów. Z racji ograniczonego czasu, napisałem testy tylko do presenterów.
4. Aktualnie, najprościej, najszybciej i najwygodniej było mi skorzystać z MVP i obecnego stosu. Być może warto rozważyć kilka zmian:
 - RxJava -> Coroutines, LiveData
 - MVP -> MVVM 
5. Otwieranie nowej aktywności można zrobić za pomocą Navigatora.
6. Room który trzyma tylko id filmów, aby sprawdzić czy ktoś polubił daną propozycję może wydawać się rozwiązaniem na wyrost.
7. Do listy można napisać DiffUtil który sam policzy czy zmieniła się stan widoku
8. Detail Activity + componenty wokół niego powinny być w osobnym module (jak now playing)

W zadaniu istnieją moduły. Mogą być one podzielone bardziej granularnie celem lepszego odseparowania klas, widoków, logik. 
Na przykładzie now-playing module można zaproponować, rozdzielenie modeli domain i view do osobnych modułów. W obecnej implementacji moduł który zostanie zaimplementowany udstępni modele domenowe, które jednak są niepożądane. Aby tego uniknąć, modele domenowe powinny wylądować w osobnym module. W taki sam sposób można byłoby "rozbić" zapytania sieciowe oraz warstwę prezentacji. 
