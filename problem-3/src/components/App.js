import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import config from '../config.js';
import SearchForm from './SearchForm';
import Nav from './Nav';
import Gallery from './Gallery';
import Galleries from './Galleries';
import Favorites from './Favorites';
import NotFound from './NotFound';

const axios = require('axios');
const image_limit = 24;


class App extends Component {

  constructor () {
    super();
    this.state = {
      loading: true,      
      galleries: [],
      favorites: [],
      search_photos: []
    };
  }
  requestGalleries = () => {
    /**
     * Creates a dynamic url and returns an axios request
     */
    const url = `https://www.flickr.com/services/rest/?method=flickr.galleries.getList&api_key=${config.apiKey}&user_id=${config.userId}&continuation=0&per_page=${image_limit}&page=1&format=json&nojsoncallback=1&content_type=1`;
    return axios.get(url);
  }
  requestFavorites = () => {
    /**
     * Creates a dynamic url and returns an axios request
     */
    const url = `https://www.flickr.com/services/rest/?method=flickr.favorites.getList&api_key=${config.apiKey}&user_id=${config.userId}&per_page=${image_limit}&page=1&format=json&nojsoncallback=1&content_type=1&sort=relevance&extras=description`;
    return axios.get(url);
  }
  requestImages = (tag) => {
    /**
     * Creates a dynamic url and returns an axios request
     */
    const url = `https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=${config.apiKey}&tags=${tag}&per_page=${image_limit}&page=1&format=json&nojsoncallback=1&content_type=1&sort=relevance&extras=description`;
    return axios.get(url);
  }

  componentDidMount = () => {
    const self = this; // helps maintain lexical scope

    axios.all([      
      this.requestGalleries(),
      this.requestFavorites()
     // this.requestImages('electric%20%guitars')
    ])
      .then(axios.spread(( galleries, favorites) => {
        // When all requests are complete, set states
        self.setState({          
          galleries: galleries.data.galleries.gallery,
          favorites: favorites.data.photos.photo,
          loading: false
        });
        
        //combinedPhotosArray = self.combinePhotos();
      }))
      .catch(error => {
        console.error('Error fetching and parsing data', error);
      });
  }

  combinePhotos = () => {
    /**
     * Combines default photos from application's state to show an even amount of photos for each category
     */
    return [      
     // ...this.state.guitar_photos.slice(0, 8)
    ];
  }

  handleSearch = (query) => {
    const self = this; // helps maintain lexical scope

    this.setState({
      loading: true
    });
    
    this.requestImages(query)
      .then(response => {
        self.setState({
          search_photos: response.data.photos.photo,
          loading: false
        });
      })
      .catch(error => {
        console.log('Error fetching and parsing data', error);
      });
  }

  render() {
    return (
      <BrowserRouter>
        <div className="container">
        <ul className="gn-submenu" role="menu" aria-label="submenu">
        
          <li className="menuitem">
          <Nav />
          </li>
          <li className="menuitem">
          <SearchForm search={this.handleSearch} />
          </li>
          </ul>

            <Switch>
              <Route
                exact path="/"
                render={() => <Gallery
                isLoading={this.state.loading}
                data={this.combinePhotos()} />}
              />              

              <Route
                path="/galleries"
                render={() => <Galleries
                isLoading={this.state.loading}
                data={this.state.galleries} />}
              />

              <Route
                path="/favorites"
                render={() => <Favorites
                isLoading={this.state.loading}
                data={this.state.favorites} />}
              />

              <Route
                path="/q/:query"
                render={() => <Gallery
                isLoading={this.state.loading}
                data={this.state.search_photos} />}
              />

              <Route component={NotFound} />
            </Switch>
          </div>
      </BrowserRouter>
    );
  }
}

export default App;