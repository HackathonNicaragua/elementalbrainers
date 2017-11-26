module ApplicationHelper
  def active_controller(controller)
    controller == controller_name ? 'active' : ''
  end

  # checkbox
  def check_if_true(item)
    if item == 'true' || item == true || item == 1 || item == '1' || item.nil?
      true
    else
      false
    end
  end

  def active_human(active)
    if active
      t('states.active')
    else
      t('states.inactive')
    end
  end
end
