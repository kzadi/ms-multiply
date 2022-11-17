/**
 * Créer par Joachim Zadi le 07/11/2022 à 17:56
 * @version 1.1
 * @author Joachim Zadi
 */

// ETAPE 1 : LES VARIABLES DE L'APPLICATION
let formJeux = document.getElementById("tentative-form");
let a = document.getElementById("multiply-a");
let b = document.getElementById("multiply-b");
let resultat = document.getElementById("resultat-tentative");
let erreur = document.getElementById("erreur");
let alias = document.getElementById("user-alias");
let btnValider = document.getElementById("btnValider");
let affichage = document.getElementById("afficheReponse");
let msgResultat = document.getElementById('message-resultat');
let tentatives = document.getElementById('tentatives-body');


/**
 * Fonction permettant de generer la multiplication de la page
 */
const initMulitiplication = () => {
    resultat.value = '';
    alias.value = '';

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        mode: 'cors',
        cache: 'default'
    }

    fetch("http://localhost:8099/api/multiply/random", options)
        .then(response => response.json())
        .then(data => {
            a.innerText = data.facteurA;
            b.innerText = data.facteurB;
        })
}

/**
 * Permettant de la validite de la saisie de la tentative
 * @param chaine La saisie à tester
 * @returns {boolean|boolean}
 */
const isInvalidSaisieResultat = (chaine) => {
    chaine = chaine.trim();
    return chaine.length === 0 || isNaN(chaine);
}

/**
 * Permet de tester la validité de la saisie de l'alias
 * @param chaine La saisie à tester
 * @returns {boolean}
 */
const isInvalideSaisieAlias = (chaine) => {
    return chaine.trim().length === 0
}

/**
 * Permet de mettre à jour le tableau des tentatives
 * @param alias
 */
const majTentatives = (alias) => {

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        mode: 'cors',
        cache: 'default'
    }

    fetch(`http://localhost:8099/api/tentatives/${alias}`, options)
        .then(response => response.json())
        .then(data => {
            tentatives.innerText = "";
            data.forEach((ligne) => {
                let tr = document.createElement("tr");
                let td = document.createElement('td');

                td.innerText = ligne.id;
                tr.appendChild(td);

                td = document.createElement('td');
                td.innerText = ligne.multiplication.facteurA + ' x ' + ligne.multiplication.facteurB;
                tr.appendChild(td);

                td = document.createElement('td');
                td.innerText = ligne.reponse;
                tr.appendChild(td);

                td = document.createElement('td');
                td.innerText = ligne.correct ? "Correct" : "Incorrect";
                tr.appendChild(td);

                tentatives.appendChild(tr);
            })
        })
}

// AU CHARGEMENT DE LA PAGE DE JEU
addEventListener("load", (e) => {
    initMulitiplication();
});

// GESTION DE LA SAISIE DE LA TENTATIVE
resultat.addEventListener("keyup", () => {
    let saisie = resultat.value;

    if (isInvalidSaisieResultat(saisie)) {
        erreur.innerText = "Merci de saisir un nombre";
        resultat.className = 'form-control is-invalid';
        btnValider.disabled = true;
    } else {
        erreur.style.display = "none"
        resultat.className = 'form-control is-valid';
        btnValider.disabled = false;
    }
});

// GESTION DE LA SAISIE DE L'ALIAS
alias.addEventListener("keyup", () => {
    let saisie = alias.value;

    if (isInvalideSaisieAlias(saisie)) {
        erreur.innerText = "Merci de saisir un alias";
        alias.className = 'form-control is-invalid';
        btnValider.disabled = true;
    } else {
        erreur.style.display = "none"
        alias.className = 'form-control is-valid';
        btnValider.disabled = false;
    }
});

// GESTION DE LA SELECTION DU CHAMP SAISIE
alias.addEventListener("selectionchange", () => {
    btnValider.disabled = !alias.value;
});

// GESTION DE LA SOUMISSION DU FORMULAIRE
formJeux.addEventListener("submit", (evt) => {
    evt.preventDefault();

    if (formJeux.noValidate) {
        formJeux.noValidate = false;
    } else {
        let facteurA = a.innerText;
        let facteurB = b.innerText;
        let resultatSaisie = resultat.value;
        let aliasSaisie = alias.value;

        let donnee = {
            user: {alias: aliasSaisie},
            multiplication: {facteurA: facteurA, facteurB: facteurB},
            reponse: resultatSaisie
        }

        let options = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(donnee)
        }

        fetch("http://localhost:8099/api/tentatives", options)
            .then(response => response.json())
            .then(data => {
                if (data.correct) {
                    msgResultat.innerText = "Le résultat est correct: FELICITATION";
                    affichage.className = "alert alert-success text-center";
                } else {
                    msgResultat.innerText = "Oups, le résultat est incorrect: REESSAYEZ";
                    affichage.className = "alert alert-danger text-center";
                }
            });
        initMulitiplication();
        majTentatives(aliasSaisie);
    }
});
