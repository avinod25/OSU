import React from 'react';
import PropTypes from 'prop-types';

const PhotoItem = ({ gallery }) => {
  return (
  
    <div className="photo-list-gallery-container">
    <div className="tile-container">
      <div className="tile tile-cover"></div>
      <div className="tile-rows">        
        <div className="tile-row">
          <div className="tile div" style={{height:'96px',width:'96px', flexBasis:'auto', overflow:'hidden',backgroundSize:'cover', backgroundImage: `url(https://farm${gallery.primary_photo_farm}.staticflickr.com/${gallery.primary_photo_server}/${gallery.primary_photo_id}_${gallery.primary_photo_secret}.jpg)`}}></div>
        </div>
      </div>
      <a className="click-target" href={gallery.url} tabIndex="-1" role="heading" aria-level="3"></a>
    </div>
    <div className="sub-photo-info-bar">
      <div className="info-container">
        <div className="details">
          <h4 className="gallery-title">
            <a href={gallery.url}>{gallery.title._content}</a>
          </h4>
          <div className="stats">
            <span className="stat item-count">
              {gallery.count_photos} items
            </span>
            <span className="stats-separator">&middot;</span>
            <span className="stat view-count">
              {gallery.count_views} view
            </span>            
          </div>
        </div>
      </div>
  
    </div>
    </div>
  );
}

PhotoItem.propTypes = {  
  gallery: PropTypes.object.isRequired
};

export default PhotoItem;
