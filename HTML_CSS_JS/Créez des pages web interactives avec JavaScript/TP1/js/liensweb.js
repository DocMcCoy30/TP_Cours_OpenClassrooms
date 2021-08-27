/* 
Activité 1
*/

// Liste des liens Web à afficher. Un lien est défini par :
// - son titre
// - son URL
// - son auteur (la personne qui l'a publié)
var listeLiens = [
    {
        titre: "So Foot",
        url: "http://sofoot.com",
        auteur: "yann.usaille"
    },
    {
        titre: "Guide d'autodéfense numérique",
        url: "http://guide.boum.org",
        auteur: "paulochon"
    },
    {
        titre: "L'encyclopédie en ligne Wikipedia",
        url: "http://Wikipedia.org",
        auteur: "annie.zette"
    }
];

function creerLien (lien) {
    var titreElt = document.createElement("a");
    titreElt.textContent = lien.titre;
    titreElt.href = lien.url;
    titreElt.style.color = "#428bca";
    titreElt.style.textDecoration = "none";
    titreElt.style.marginRight = "5px";
    
    var urlElt = document.createElement("span")
    urlElt.textContent = lien.url;
    

    var ligneTitre = document.createElement("h4");
    ligneTitre.style.margin = "0px";
    ligneTitre.appendChild(titreElt);
    ligneTitre.appendChild(urlElt);
    
    var auteurElt = document.createElement("span");
    auteurElt.textContent = "Ajouté par "+ lien.auteur;

    var divElt = document.createElement("div");
    divElt.classList.add("lien")
    divElt.appendChild(ligneTitre);
    divElt.appendChild(auteurElt);

    return divElt
}

var contenu = document.getElementById("contenu");
 //methode 1 : boucle for
/*for (var i = 0; i<listeLiens.length; i++) {
    var lienElt = creerLien(listeLiens[i]);
    contenu.appendChild(lienElt);
}*/


//methode 2 : foreach
listeLiens.forEach(lien => {
    var lienElt = creerLien(lien);
    contenu.appendChild(lienElt);
});

