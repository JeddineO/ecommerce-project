<%@ page import="com.app.boutique.bo.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Admin admin = (Admin) request.getSession().getAttribute("admin");
    if (admin == null)
    {
        request.getSession().setAttribute("error","Vous devez se connecter");
        response.sendRedirect(request.getContextPath() + "/adminLogin");
    }

%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="resources/style.css">
    <style>
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
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            box-sizing: border-box;
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
    </style>

</head>

<body>


<div class="navbar">
    <a href="AddProduct">Ajouter Produit</a>
    <a href="ListProduits">Liste des Produits</a>
    <a class="active" href="categorie">Gestion des Catégories</a>
    <a href="#" style="position:fixed;left:10px"><i class="fas fa-user"></i>
        <%if(admin!=null){%>
        <%=admin.getNom()%>
        <%=admin.getPrenom()%>
        <%}%>
    </a>
    <a href="${pageContext.request.contextPath}/logout" style="position:fixed;right:10px" onclick="Deconnect()"></i>
        Déconnecter
    </a>
</div>
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
<div>

    <div class="container">
        <h1>Gestion des Catégories!</h1>
        <div>
            <h2>Ajouter Catégorie</h2>
            <form action="${pageContext.request.contextPath}/AddCategorie" method="post">
                <input type="text" name="nomCategorie" placeholder="Nom du Catégorie" required>
                <input type="submit" class="btn" value="Ajouter Catégorie">
            </form>

        </div>
    <h2>Liste des Catégories</h2>
    <table>
        <thead>
        <tr>
            <th>Nom Du Catégorie</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="categorie" items="${categories}">
            <tr>

                <td>${categorie.getNomCategorie()}</td>
                <td class="actions">
                    <button class="btn" onclick="showEditForm('${categorie.getIdCategorie()}', '${categorie.getNomCategorie()}')">Modifier</button>
                    <button class="btn btn-danger" onclick="showDeleteForm('${categorie.getIdCategorie()}')">Supprimer</button>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    </div>
</div>

<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeEditForm()">&times;</span>
        <h2>Modifier Produit</h2>
        <form action="${pageContext.request.contextPath}/UpdateCategorie" method="post">
            <input type="hidden" id="idCategorie" name="idCategorie">
            <input type="text" name="CategorieName" id="editCategorieName" placeholder="Product Name" required>
            <input type="submit" value="Modifier Catégorie" style="cursor: pointer;">
        </form>
    </div>
</div>

<div id="deleteModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeDeleteForm()">&times;</span>
        <h2>Supprimer Produit</h2>
        <form action="${pageContext.request.contextPath}/DeleteCategorie" method="post">
            <h4>Confirmer l'action de suppression</h4>
            <input type="hidden" id="idDeleteCategorie" name="idCategorie">
            <input type="submit" value="Confimer" style="width: 40%;cursor: pointer">
            <input type="button" value="Annuler" onclick="closeDeleteForm()" style="width: 40%;background-color: #da190b;color: #dddddd;cursor: pointer">
        </form>
    </div>
</div>

<script>
    function showEditForm(categorieId,NomCategorie) {
        document.getElementById('idCategorie').value=categorieId;
        document.getElementById('editCategorieName').value = NomCategorie;
        document.getElementById('editModal').style.display = 'block';
    }

    function showDeleteForm(categorieId) {
        document.getElementById('idDeleteCategorie').value = categorieId;
        document.getElementById('deleteModal').style.display = 'block';
    }

    function closeDeleteForm() {
        document.getElementById('deleteModal').style.display = 'none';
    }

    function closeEditForm() {
        document.getElementById('editModal').style.display = 'none';
    }
</script>
</body>

</html>