import React from 'react';
import PropTypes from 'prop-types';
import GalleryItem from './GalleryItem';
import Loader from './Loader';
import NotFound from './NotFound';

const Galleries = ({ data, isLoading }) => {
  let results;

  if (isLoading) {

    results = <Loader />;
    
  } else {
    
    if (Array.isArray(data) && data.length > 0) {
      results = data.map((gallery, index) => (
        <GalleryItem
          key={index}
          gallery= {gallery}
         />
      ));
    } else if (!isLoading) {
      // Assign Not Found list item if no photos are provided
      results = <NotFound />;
    }
  }
  
  return (
    <div className="photo-container">
      <h2>Galleries</h2>
      <div role="main" className="galleries-list-container fluid-centered ">
        <div  className="view galleries-list-toolbar-view requiredToShowOnServer">
          <div className="toolbar-container">
            <a role="button" className="new-gallery-button" href="#">
              <svg className="icon icon-add_to_gallery"><use xlinkHref="#icon-add_to_gallery"></use></svg>
              <span>New Gallery</span>
            </a>
          </div>
        </div>
        <div  className="view photo-list-view requiredToShowOnServer" style={{height: '272px'}}>
          {results}
        </div>
      </div>
    </div>
  );
}

Galleries.propTypes = {
  data: PropTypes.array,
  isloading: PropTypes.bool
};

export default Galleries;
