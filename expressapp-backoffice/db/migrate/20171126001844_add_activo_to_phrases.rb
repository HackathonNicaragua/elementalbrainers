class AddActivoToPhrases < ActiveRecord::Migration[5.0]
  def change
    add_column :phrases, :activo, :boolean
  end
end
