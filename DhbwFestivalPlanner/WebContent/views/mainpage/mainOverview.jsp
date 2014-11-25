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

                    Zugesagt:
                    <ul id="zgaeste">

                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                    </ul>
                </div>
                <div id="vgaesteliste" class="gaestel">Vielleicht:
                    <ul id="vgaeste">
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                        <li class="dummy">test2
                        </li>
                        <li class="dummy">test3
                        </li>
                    </ul>
                </div>

            </div>
            <div id="rightside">
                <div id="yeah">
                    <a class="icons" href="#" id="createevent">
                        <i class="fa fa-plus fontawicons"></i> NEUE PARTY</a>
                    <div class="clear"></div>
                </div>



                <div id="veranstaltungsliste" class="gaestel">
                    <ul id="navi">
                        <li id="vlistenheader" class="dummy">Deine Veranstaltungen:</li>
                        <c:forEach items="${events}" var="event">
                        <li class="dummy"><a href="#">${event.name}</a>
                            <ul>
                                <!-- steht für veranstaltungsseite bearbeiten-->
                                <li class="dropdo"><a href="#"><i class="fa fa-pencil"></i></a>
                                </li>
                                <!-- steht für veranstaltung absagen/löschen-->
                                <li class="dropdo"><a href="#"><i class="fa fa-times-circle"></i></a>
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