module ApplicationHelper
  def active_controller(controller)
    controller == controller_name ? 'active' : ''
  end
end
