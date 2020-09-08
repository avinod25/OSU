import React from 'react';
import PropTypes from 'prop-types';

const PhotoItem = ({ url, title }) => {
  return (
    <li>
      <div>
        <div><label>{title}</label></div>
        <div className="photo"><img src={url} alt={title} /></div>
      </div>
    </li>
  );
}

PhotoItem.propTypes = {
  url: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired
};

export default PhotoItem;
