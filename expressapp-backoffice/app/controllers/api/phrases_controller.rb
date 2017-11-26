class Api::PhrasesController < ApplicationController
  skip_action :authenticate_user!, :only => [:search]

  def search
    frases = params[:frases].collect { |x| '%' + x + '%' }
    phrases = Phrase.actives.where('frase ILIKE ANY ( array[?] )', frases)

    render json: { frases: phrases }
  end
end
