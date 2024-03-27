<%@ page import="com.app.boutique.bo.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Admin admin = (Admin) request.getSession().getAttribute("admin");
    if (admin == null)
    {
        response.sendRedirect(request.getContextPath() + "/loginSession");
    }

%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="resources/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


</head>
<style>
    input[type="file"] {
        width: 100%;
        padding: 10px;
        margin: 5px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        background-color: #fff; /* Background color */
        color: #333; /* Text color */
    }

    input[type="file"]:hover {
        border-color: #666; /* Border color on hover */
    }

</style>

<body>





<div class="navbar">
    <a class="active" href="AddProduct">Ajouter Produit</a>
    <a href="ListProduits">Liste des Produits</a>
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



<div class="container">
    <h1>Bienvenue,
        <%if(admin!=null){%>
        <%=admin.getNom()%>
        <%=admin.getPrenom()%>
        <%}%>
    </h1>
    <div>
        <h2>Ajouter Produit</h2>
        <form action="${pageContext.request.contextPath}/AddProduct" method="post" enctype="multipart/form-data">
            <input type="text" name="nomProduit" placeholder="Nom du produit" required>
            <input type="number" name="prix" placeholder="Prix" min="0" step="0.01" required>
            <select name="categorie">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.idCategorie}">${category.nomCategorie}</option>
                </c:forEach>
            </select>
            <input type="number" name="stock" placeholder="Stock" min="0" step="1" required>


            <input type="submit" class="btn" value="Ajouter Produit">
        </form>
    </div>
</div>

<script>
    function previewImage(input) {
        var preview = document.getElementById('preview');
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                preview.src = e.target.result;
                preview.style.display = 'block';
                preview.style.width='200px';
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            preview.src = '#';
            preview.style.display = 'none';
        }
    }
</script>
</body>

</html>