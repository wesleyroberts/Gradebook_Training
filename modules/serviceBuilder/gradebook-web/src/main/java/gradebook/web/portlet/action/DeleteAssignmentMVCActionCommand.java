package gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import gradebook.service.AssignmentService;
import gradebook.web.constants.GradebookWebPortletKeys;
import gradebook.web.constants.MVCCommandNames;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Action Command for deleting assignments.
 *
 * @author liferay
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + GradebookWebPortletKeys.GRADEBOOKWEB,
                "mvc.command.name=" + MVCCommandNames.DELETE_ASSIGNMENT
        },
        service = MVCActionCommand.class
)
public class DeleteAssignmentMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        // Get assignment id from request.

        long assignmentId = ParamUtil.getLong(actionRequest, "assignmentId");

        try {

            // Call service to delete the assignment.

            _assignmentService.deleteAssignment(assignmentId);

        }
        catch (PortalException pe) {
            pe.printStackTrace();
        }

    }

    @Reference
    protected AssignmentService _assignmentService;
}
