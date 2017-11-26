class Api::PhrasesController < ApplicationController
  skip_before_action :authenticate_user!, :only => [:search]
  skip_before_action :verify_authenticity_token, :only => [:search]

  def search
    if params[:frases].size > 0
      frases = params[:frases].collect { |x| '%' + x + '%' }
      phrases = Phrase.actives.where('frase ILIKE ANY ( array[?] )', frases)
      render json: phrases.to_json
    else
      render json: {}
    end
  end
end
