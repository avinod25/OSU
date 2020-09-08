import React from 'react';

export const NewGalleryForm = ({ onSubmit }) => {
  return (
    <form onSubmit={onSubmit}>
        <div className="stuff-creation-container ">
            <header><h1 className="stuff-creation-heading">Create a new gallery</h1></header>
      <div className="input-item title-input-item form-group">
        <label htmlFor="name">Gallery name</label>
        <input className="input-field stuff-creation-title form-control" id="name" />
      </div>
      <div className="form-group">
        <label htmlFor="description">Description</label>
        <textarea          
          className="input-field stuff-creation-description form-control"
          id="description"
          placeholder="Description (optional)"
        />
      </div>
      <div className="form-group">        
        <button className="form-control btn btn-primary" type="submit">
          Create
        </button>
      </div>
      </div>
    </form>
  );
};
export default NewGalleryForm;
