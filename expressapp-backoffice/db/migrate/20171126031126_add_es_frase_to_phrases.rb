class AddEsFraseToPhrases < ActiveRecord::Migration[5.0]
  def change
    add_column :phrases, :es_frase, :boolean
  end
end
