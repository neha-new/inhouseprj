package com.inhouse.core.servlets;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import javax.jcr.Node;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = "inhouse/components/page",
        methods = HttpConstants.METHOD_GET,
        selectors = "vivek",
        extensions = "html")
@ServiceDescription("Demo Servlet Description")
public class DemoServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        Page page = resource.getParent().adaptTo(Page.class);
        resp.setContentType("text/plain");
        ArrayList<String> pageNameList = new ArrayList();

        Iterator<Page> pageIterator = page.listChildren();
        while(pageIterator.hasNext()) {
            String pageName = pageIterator.next().getName();
            pageNameList.add(pageName);

        }

        resp.getWriter().write("pageTitle = " + page.listChildren());
    }
}

