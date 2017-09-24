<%@page import="dao.DAO"%>
<%@page import="java.util.List"%>
<%@page import="bean.produit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/CaissePressToPlay/CSS/styles.css">
<script type="text/javascript"
	src="/CaissePressToPlay/JS/requestLauncher.js"></script>
<script type="text/javascript">
	var webAddress = "http://192.168.1.254:8080/CaissePressToPlay/BDDAccessServlet";
</script>
<title>Caisse PressToPlay</title>
</head>
<body>

<ul>
	<% 
    DAO dao = new DAO(); 
    dao.ouvrir(); 
     
    List<produit> produits = dao.listerProduits();
    
    for (produit prod : produits) { %>
    	<li><label class="nom" ><%=prod.getNom() %></label>
			<input class="input" type="number" min=1 value=1 id="<%=prod.getId()%>" onchange="updatePrix('<%=prod.getId()%>', '<%=prod.getPrix()%>')"/>
			<label class="prix" id="label<%=prod.getId() %>"><%=prod.getPrix() %></label>€
    		<input class="input button add" type="button" value="+" onclick="incremente('<%=prod.getId()%>')"/>
    		<input class="input button add" type="button" value="-" onclick="decremente('<%=prod.getId()%>')"/>
    	</li>
    	
    <%}
    dao.fermer(); 
    
    %>
</ul>

<audio hidden controls="controls" preload>
	<source type="audio/wav" src="Ressource/caisse.wav">
	Votre navigateur n’est pas compatible
</audio>
</body>
</html>