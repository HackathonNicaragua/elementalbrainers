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

require 'test_helper'

class PhraseTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end
end
