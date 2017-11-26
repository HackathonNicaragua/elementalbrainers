Rails.application.routes.draw do
  resources :phrases

  root 'home#index'

  devise_for :users, :controllers => { :sessions => 'sessions' }
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
