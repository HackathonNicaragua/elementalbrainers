# == Schema Information
#
# Table name: phrases
#
#  id         :integer          not null, primary key
#  frase      :string
#  imagen     :string
#  created_at :datetime         not null
#  updated_at :datetime         not null
#  activo     :boolean
#  es_frase   :boolean
#

class Phrase < ApplicationRecord
  validates :frase, presence: true
  validates :imagen, presence: true

  # carrierwave helper
  mount_uploader :imagen, ImagenUploader
end
