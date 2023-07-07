# Kframe
Pour ce framework,il faut configurer le fichier web.xml ,ajouter cette ligne
<servlet>
        <servlet-name>FrontServlet</servlet-name>
        <servlet-class>etu1790.framework.servlet.FrontServlet</servlet-class>
</servlet>
<servlet-mapping>
        <servlet-name>FrontServlet</servlet-name>
        <url-pattern>/</url-pattern>
</servlet-mapping>
Il faut que vos classes ont un constructeur vide
Si vous passez par un formulaire,pour que l'instanciation soit bien faite,il faut que le nom de l;input soit le meme que selui de l'attribut ou des arguments de la fonction
Il faut ajouter le FW.jar au variable d'environnement
