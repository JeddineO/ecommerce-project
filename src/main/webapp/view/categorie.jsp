<%@ page import="com.app.boutique.bo.Client" %>
<%@ page import="com.app.boutique.bo.Vente" %>
<%@ page import="com.app.boutique.bo.Commande" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    Client client = (Client) request.getSession().getAttribute("client");
    Vente vente = (Vente) request.getSession().getAttribute("vente");
%>

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
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.5);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        /* Style du modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
            border-radius: 5px;
            position: relative;
        }

        /* Style pour le bouton de fermeture */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        /* Style du modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
            border-radius: 5px;
            position: relative;
        }

        /* Style pour le bouton de fermeture */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        /* Style pour le contenu du panier */
        .panier-content {
            margin-top: 20px;
        }

        /* Style pour le tableau du panier */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border-bottom: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Style pour les en-têtes du tableau */
        thead th {
            font-weight: bold;
        }

        /* Style pour les lignes impaires du tableau */
        tbody tr:nth-child(odd) {
            background-color: #f9f9f9;
        }

        /* Style pour les lignes du tableau */
        tbody tr:hover {
            background-color: #f2f2f2;
        }

        /* Style pour le total */
        .total {
            float: right;
            margin-top: 10px;
        }

        /* Style pour le bouton Payer */
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
        .error-message {
            border:none;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px; /* Rounded corners */
            text-align: center;
        }



    </style>

</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/store"></a>
    <h1>Ma Boutique en Ligne</h1>
    <nav>
        <ul>
            <c:forEach var="category" items="${categories}">
                <li <c:if test="${category.idCategorie eq categorie.idCategorie}">class="active"</c:if>>
                    <a href="store?id=${category.idCategorie}">${category.nomCategorie}</a>
                </li>
            </c:forEach>
            <%
                if (client==null) {
            %>
            <li class="auth"><a href="${pageContext.request.contextPath}/login"><i class="fas fa-user"></i> S'identifier</a></li>
            <li class="auth"><a href="${pageContext.request.contextPath}/register"><i class="fas fa-user-plus"></i> S'inscrire</a></li>
            <%} else {%>
            <li class="auth"><a href="#"><i class="fas fa-user-plus"></i> <%=client.getNom()%></a></li>
            <%}%>
            <li><a href="#" onclick="showPanier()"><i class="fas fa-shopping-cart"></i> Panier</a></li>
        </ul>
    </nav>
</header>

<main>
    <% if (request.getSession().getAttribute("error") != null) { %>
    <div class="error-message" style="color: red;background-color: #ffe6e6;">
        <%= request.getSession().getAttribute("error") %>
        <% session.removeAttribute("error");%>
    </div>
    <% } %>

    <% if (request.getSession().getAttribute("success") != null) { %>
    <div class="error-message" style="color: #387500;background-color: #aed99e;">
        <%= request.getSession().getAttribute("success") %>
        <% session.removeAttribute("success");%>
    </div>
    <% } %>






        <section id="categorie${categorie.idCategorie}" class="categorie">
            <h2>${categorie.nomCategorie}</h2>
            <div class="produits-container">
                <c:forEach var="produit" items="${categorie.produits}">
                    <div class="produit">
                        <img src="resources/${empty produit.image ? 'prod.jpg' : produit.image}" height="300px">

                        <h3>${produit.nomProduit}</h3>
                        <b style="color: #4CAF50">${produit.prix} dh</b>
                        <form action="${pageContext.request.contextPath}/addPanier" method="post">
                            <input type="hidden" value="${produit.idProduit}" name="idProduit">
                            <input type="number" name="quantite" min="1" value="1" max="${produit.stock}">
                            <button type="submit">Ajouter au Panier</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </section>



</main>

<div id="PanierModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeDeleteForm()">&times;</span>
        <h2>Panier</h2>
        <div id="panier-content" class="panier-content"></div>
        <form action="${pageContext.request.contextPath}/Payer" method="post">

            <div class="details-produit">

                <%
                    if (vente != null) {
                        List<Commande> commandes = vente.getCommandes();
                %>
                <table>
                    <thead>
                    <tr>
                        <th>Nom du Produit</th>
                        <th>Quantité</th>
                        <th>Prix unitaire</th>
                        <th>Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% double Total =0 ;%>
                    <% for (Commande commande : commandes) { %>
                    <tr>
                        <td><%= commande.getProduit().getNomProduit() %></td>
                        <td><%= commande.getQuantité() %></td>
                        <td><%= commande.getProduit().getPrix() %></td>
                        <td><%= commande.getQuantité() * commande.getProduit().getPrix() %></td>
                        <% Total  =Total+ commande.getQuantité() * commande.getProduit().getPrix();%>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
                <div >
                    <h3>Totale : <%=Total%></h3>

                    <button>Payer</button>
                </div>

                <%
                } else {
                %>
                <h2>Votre panier est vide</h2>
                <%
                    }
                %>
            </div>

            <script>
                function showPanier() {
                    //document.getElementById('idDeleteCategorie').value = categorieId;
                    document.getElementById('PanierModal').style.display = 'block';
                }

                function closeDeleteForm() {
                    document.getElementById('PanierModal').style.display = 'none';
                }

            </script>

</body>
</html>
