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

// TP1
function creerLien (lien) {
    var titreElt = document.createElement("a");
    titreElt.textContent = lien.titre;
    titreElt.href = lien.url;
    titreElt.style.color = "#428bca";
    titreElt.style.textDecoration = "none";
    titreElt.style.marginRight = "5px";
    
    var urlElt = document.createElement("span");
    urlElt.textContent = lien.url;
    

    var ligneTitre = document.createElement("h4");
    ligneTitre.style.margin = "0px";
    ligneTitre.appendChild(titreElt);
    ligneTitre.appendChild(urlElt);
    
    var auteurElt = document.createElement("span");
    auteurElt.textContent = "Ajouté par "+ lien.auteur;

    var divElt = document.createElement("div");
    divElt.classList.add("lien");
    divElt.appendChild(ligneTitre);
    divElt.appendChild(auteurElt);

    return divElt
}

var contenu = document.getElementById("contenu");

function afficherLiens (tableau) {
    tableau.forEach(lien => {
        var lienElt = creerLien(lien);
        contenu.appendChild(lienElt);
});
}

afficherLiens (listeLiens);


// TP 2 

//création du bouton "ajouter un lien"
var ajoutBouton = document.createElement("button");                      
ajoutBouton.id = "ajoutBouton";  
ajoutBouton.style.width = "150px";                                       
ajoutBouton.style.borderRadius = "5px";
ajoutBouton.style.padding = "5px";
ajoutBouton.style.marginBottom = "3%";
ajoutBouton.appendChild(document.createTextNode("Ajouter un lien")); 
document.body.insertBefore(ajoutBouton, contenu);     

//creation du message de confirmation : les éléments de style sont en css pour alleger le code
var divMessage = document.createElement("div");
divMessage.id = "divMessage";
divMessage.style.display = "none";
divMessage.appendChild(document.createTextNode("Le lien a bien été ajouté."));
document.body.insertBefore(divMessage, ajoutBouton); 

//fonction de création du formulaire
function creerFormulaire () {
    //formulaire
    var formElt = document.createElement("form");
    formElt.id = "formElt";
    formElt.style.marginBottom = "30px";
    document.body.insertBefore(formElt, contenu);
    
    //input nom de l'auteur
    var auteurForm = document.createElement("input");                          
    auteurForm.id = "auteurForm";
    auteurForm.setAttribute("type", "text");
    auteurForm.setAttribute("placeholder", "Entrez votre nom");
    auteurForm.setAttribute("required", "required");
    auteurForm.style.marginRight = "30px";
    auteurForm.style.width = "250px";
    auteurForm.textContent = "";
    formElt.appendChild(auteurForm);
    
    //input titre du lien
    var titreForm = document.createElement("input");                          
    titreForm.id = "titreForm";
    titreForm.setAttribute("type", "text");
    titreForm.setAttribute("placeholder", "Entrez le titre du lien")
    titreForm.setAttribute("required", "required");
    titreForm.style.marginRight = "30px";
    titreForm.style.width = "400px";
    titreForm.textContent = "";
    formElt.appendChild(titreForm);
    
    //input url du lien
    var urlForm = document.createElement("input");                          
    urlForm.id = "urlForm";
    urlForm.setAttribute("type", "text");
    urlForm.setAttribute("placeholder", "Entrez l'URL du lien")
    urlForm.setAttribute("required", "required");
    urlForm.style.marginRight = "30px";
    urlForm.style.width = "400px";
    urlForm.textContent = "";
    formElt.appendChild(urlForm);
    
    //bouton validation
    var submitBoutton = document.createElement("input");
    submitBoutton.id = "submitBouton";
    submitBoutton.setAttribute("type", "submit");
    submitBoutton.style.width = "150px";                                       
    submitBoutton.style.borderRadius = "5px";
    submitBoutton.style.padding = "5px";
    submitBoutton.style.marginLeft = "20px";
    submitBoutton.appendChild(document.createTextNode("Ajouter")); 
    formElt.appendChild(submitBoutton);

    return formElt;
}

//fonction focus sur les différents input
function formFocus (input) {
input.addEventListener("focus", function () {
    input.style.borderColor = "#428bca";
});
input.addEventListener("blur", function (e) {
    input.style.borderColor = "black";
});
}

//fonction test de l'URL
function testerURL (url) {
    var regex = / ^(http(s?)):\/\ /;
    var regexURL = "";
    if (regex.test(url)) {
        regexURL = url;
    } else {
        regexURL = "http://"+url;
    }
    return regexURL;
}

//fonction création objet nouveau lien
function creerNewLien () {
    var newLien = {
        titre: titreForm.value,
        url: testerURL(urlForm.value),
        auteur :auteurForm.value
    };
    return newLien;
}

//validation du formulaire
ajoutBouton.addEventListener("click", function() {
    ajoutBouton.style.display = "none";
    creerFormulaire();
    formFocus(auteurForm);
    formFocus(titreForm);
    formFocus(urlForm);
    formElt.addEventListener("submit", function(e) {
        divMessage.style.display = "block";
        ajoutBouton.style.display = "block";
        var nouveauLien = (creerLien(creerNewLien()));
        document.getElementById("contenu").insertAdjacentElement("afterbegin", nouveauLien);
        document.body.removeChild(document.getElementById("formElt"));
        setTimeout(function() {
            divMessage.style.display = "none";
        }, 2000);
        e.preventDefault();
    });
});





