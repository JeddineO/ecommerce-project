<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ma Boutique en Ligne</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            text-align: center;
        }

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        nav ul li {
            display: inline-block;
            margin-right: 20px;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
        }

        main {
            padding: 20px;
        }

        section {
            background-color: #fff;
            margin-bottom: 30px;
            padding: 20px;
            border-radius: 5px;
        }

        .produits-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .produit {
            width: calc(30% - 20px);
            margin-bottom: 20px;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .produit img {
            width: 100%;
            height: auto;
            border-radius: 5px;
        }

        .quantite {
            margin-top: 10px;
        }

        .quantite label {
            font-weight: bold;
        }

        .quantite input[type="number"] {
            width: 50px;
            padding: 5px;
            margin-right: 10px;
            border: 1px solid #ddd;
            border-radius: 3px;
        }

        .quantite button {
            background-color: #333;
            color: #fff;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 3px;
        }

        .quantite button:hover {
            background-color: #555;
        }
    </style>

</head>
<body>
<header>
    <h1>Ma Boutique en Ligne</h1>
    <nav>
        <ul>
            <c:forEach var="categorie" items="${categories}">
                <li><a href="${categorie.idCategorie}">${categorie.nomCategorie}</a></li>
            </c:forEach>

            <li class="auth"><a href="#"><i class="fas fa-user"></i> S'identifier</a></li>
            <li class="auth"><a href="#"><i class="fas fa-user-plus"></i> S'inscrire</a></li>
            <li><a href="#"><i class="fas fa-shopping-cart"></i> Panier</a></li>
        </ul>
    </nav>
</header>

<main>

    <c:forEach var="categorie" items="${categories}">
        <section id="categorie${categorie.idCategorie}" class="categorie">
            <h2>${categorie.nomCategorie}</h2>
            <div class="produits-container">
                <c:forEach var="produit" items="${categorie.produits}">
                    <div class="produit">
                        <img src="resources/prod.jpg" alt="">
                        <p>
                        <h3>${produit.nomProduit}</h3>
                            ${produit.prix} dh</p>
                        <div class="quantite">

                            <label>Quantité </label>
                            <input type="number" min="1" value="1">
                            <button>Ajouter au Panier</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </c:forEach>
</main>


</body>
</html>
