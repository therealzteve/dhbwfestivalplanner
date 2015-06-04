<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<tiles:insertTemplate   template="/views/style/template/mainFrame.jsp">
		
		<%-- Page Title: --%>
		<tiles:putAttribute name="pageTitle" value="Deine Events:"></tiles:putAttribute>
		
		
		
		<%-- Body of List Page --%>
		<tiles:putAttribute name="content">
		    <div id="content">
        <div id="contentwrapper">
            <div id="leftside">
                <div id="blah">
                    <a id="teiln" class="icons"><i class="fa fa-wrench fontawicons" id="wrench"></i>TEILNEHMER</a>
                    <div class="clear"></div>
                </div>


 <div id="gaesteliste" class="gaestel">

                    <p>  Zugesagt:</p><br>
                    <ul id="zgaeste">
                    </ul>
                </div>
                <div id="vgaesteliste" class="gaestel"><p>Vielleicht:</p>
    <br>
                    <ul id="vgaeste">
                    </ul>
                </div>

            </div>
             <div id="rightside">
                <div id="yeah">
                    <a class="icons" href="<c:url value="/event/edit"></c:url>" id="createevent">
                        <i class="fa fa-plus fontawicons"></i> NEUE PARTY</a>
                    <div class="clear"></div>
                </div>



                <div id="veranstaltungsliste" class="gaestel">
                    <ul id="navi">
                        <li id="vlistenheader" class="dummy">Deine Veranstaltungen:</li>
                        <c:forEach items="${events}" var="event">
                        
                        <li class="dummy"><a href="#" onclick="fetchguests(${event.id})">${event.title}</a>
                            <ul>
                                <!-- steht für veranstaltungsseite bearbeiten-->
                                <li class="dropdo"><a href="<c:url value="/event/edit?id=${event.id}"></c:url>" ><i class="fa fa-pencil"></i></a>
                                </li>
                                <!-- steht für veranstaltung absagen/löschen-->
                                
                                <li class="dropdo" ><a onclick="deleteEvent(${event.id},event)"><i class="fa fa-times-circle"></i></a>
                                </li>
                            </ul>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

        </div>
    </div>
		</tiles:putAttribute>
		
	</tiles:insertTemplate>