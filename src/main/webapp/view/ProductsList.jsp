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
    <a class="active" href="ListProduits">Liste des Produits</a>
    <a href="categorie">Gestion des Catégories</a>
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
    <h2>Liste des produits</h2>
    <table>
        <thead>
        <tr>
            <th>Nom Du Produit</th>
            <th>Prix</th>
            <th>Stock</th>
            <th>Categorie</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>

            <c:forEach var="produit" items="${produits}">
            <tr>
                <td>${produit.nomProduit}</td>
                <td>${produit.prix} dh</td>
                <td>${produit.getStock()}</td>
                <td>${produit.categorie.getNomCategorie()}</td>
                <td class="actions">
                    <button class="btn" onclick="showEditForm('${produit.idProduit}', '${produit.nomProduit}', '${produit.prix}','${produit.getStock()}','${produit.categorie.getIdCategorie()}')">Modifier</button>
                    <button class="btn btn-danger" onclick="showDeleteForm('${produit.idProduit}')">Supprimer</button>
                </td>
            </tr>
            </c:forEach>

        </tbody>
    </table>
</div>

<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeEditForm()">&times;</span>
        <h2>Modifier Produit</h2>
        <form action="${pageContext.request.contextPath}/UpdateProduct" method="post">
            <input type="hidden" id="idProduit" name="idProduit">
            <input type="text" name="editproductName" id="editProductName" placeholder="Product Name" required>
            <input type="number" name="editprice" id="editPrice" placeholder="Price" min="0" step="0.01" required>
            <input type="number" name="editstock" id="editstock" placeholder="Price" min="0" step="1" required>

            <select name="categorie">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.idCategorie}" id="item${category.idCategorie}">${category.nomCategorie}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Modifier Produit" style="cursor: pointer;">
        </form>
    </div>
</div>

<div id="deleteModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeDeleteForm()">&times;</span>
        <h2>Supprimer Produit</h2>
        <form action="${pageContext.request.contextPath}/DeleteProduct" method="post">
            <h4>Confirmer l'action de suppression</h4>
            <input type="hidden" id="idDeleteProduit" name="idProduit">
            <input type="submit" value="Confimer" style="width: 40%;cursor: pointer">
            <input type="button" value="Annuler" onclick="closeDeleteForm()" style="width: 40%;background-color: #da190b;color: #dddddd;cursor: pointer">
        </form>
    </div>
</div>

<script>
    function showEditForm(productId, productName, price,stock,categorie) {
        document.getElementById('idProduit').value=productId;
        document.getElementById('editProductName').value = productName;
        document.getElementById('editPrice').value = price;
        document.getElementById('item'+categorie).selected = true;
        document.getElementById('editstock').value = stock;
        document.getElementById('editModal').style.display = 'block';
    }

    function showDeleteForm(productId) {
        document.getElementById('idDeleteProduit').value = productId;
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