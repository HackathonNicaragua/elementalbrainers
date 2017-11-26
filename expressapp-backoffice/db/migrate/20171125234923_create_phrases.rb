class CreatePhrases < ActiveRecord::Migration[5.0]
  def change
    create_table :phrases do |t|
      t.string :frase
      t.string :imagen

      t.timestamps
    end
  end
end
