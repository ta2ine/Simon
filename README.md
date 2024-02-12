Le projet SIMON est inspiré du célèbre jeu de société Simon, où les joueurs doivent reproduire une séquence croissante de couleurs. 
Cette version du jeu est développée en utilisant le langage de programmation Kotlin.

Fonctionnalités :

Génération aléatoire de séquences de couleurs.
Interface utilisateur conviviale permettant aux joueurs d'interagir avec le jeu.
Gestion des scores des utilisateurs avec un classement des meilleurs scores mondiaux.
Trois modes de difficultés.
L'autentification des utilisauteurs (Login/Logout/Register).
Partage de son score avec tout les réseaux sociaux possédés par l'utilisateur.

Instructions :

L'utilisateur doit se connecter à son compte ou bien en créer un.
L'utilisateur doit ensuite choisir son mode de jeu parmi trois proposés.
Lorsque le jeu démarre, une séquence de couleurs est générée et présentée au joueur.
Le joueur doit reproduire la séquence en appuyant sur les boutons correspondants dans le bon ordre.
Si le joueur réussit, la séquence devient plus longue et plus complexe.
En cas d'erreur, le jeu se termine et affiche le score du joueur.
Le score du joueur en plus d'être affiché à la fin est affiché pendant la partie.
L'utilisateur peut à la fin de la partie rejouer dans le mode de difficulté qu'il souhaite ou bien partager son score avec ses amis.

Nom du projet: SIMON

Nom des membres du groupe: BASQUE Martin
                           BOUET Félix
                           LUYSSAERT Edouard

Remarques: Nous avons rencontré des difficultés avec FireBase ont nécessité un investissement important de notre temps. 
Nous avons fini par reconfigurer le paramétrage à l'aide d'un fichier json récupéré directement depuis firebase (google-services.json) , cela a permit à l'application de pouvoir envoyer les données et de les récupérer sur Firebase, sans cela le lien ne fonctionnait pas. 
Ce qui nous a occupé un certain temps était de comprendre cette manipulation, car elle ne levait aucune erreur et l'authentification sur Firebase fonctionnait, l'erreur ne concernait que la collection.
Nous avons réussi à résoudre ce problème ainsi que d'autres difficultés mineures rencontrées tout au long du projet à l'image de la gestion de la coroutine.
