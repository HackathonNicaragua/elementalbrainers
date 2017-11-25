Rails.application.routes.draw do
  # Home
  get '/', to: 'home#index', as: 'root'

  devise_for :users, :controllers => { :sessions => 'sessions' }
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
