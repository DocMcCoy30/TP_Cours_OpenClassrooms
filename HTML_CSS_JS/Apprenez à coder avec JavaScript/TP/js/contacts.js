class Contact {
    constructor (nom, prenom) {
        this.nom=nom;
        this.prenom=prenom
    }
    afficherContact () {
        return this.nom + ", " + this.prenom;
    }
}

let contacts = [];

function ajouterContact () {
    let nom = prompt("Entrez le nom :","");
    let prenom = prompt("Entrez le prénom :","");
    const nouveauContact = new Contact (nom, prenom);
    console.log(nouveauContact);
    contacts.push(nouveauContact);
    console.log("Le contact a bien été enregistré !")  
}

function afficherListeContacts () {
    if (contacts.length>0){
        for (let i = 0; i < contacts.length; i++) {
            console.log(i+1 + " - " + contacts[i].afficherContact());
        }
    } else {
        console.log("Il n'y a pas de contact enregistré.")
    }
    
}

function quitter () {
    window.close;
}
       
console.log("Bienvenu dans le gestionnaire de contact !");
let choixUtil = "";

while (choixUtil!=="0") {
    console.log("1 - Lister les contacts.")
    console.log("2 - Ajouter un contact.")
    console.log("0 - Quitter")
    choixUtil = prompt("Quel est votre choix ?","");
    switch (choixUtil) {
        case "1" : afficherListeContacts();
        break;
        case "2" : ajouterContact();
        break;
    }
}
window.close;

    



    
