import React, { Component } from 'react';
import { NewGalleryModal } from './NewGalleryModal';
import TriggerNewGalleryButton from '../TriggerNewGalleryButton';
import config from '../../config.js';
const axios = require('axios');
export class AddGallery extends Component {
  state = { isShown: false };
  showModal = () => {
    this.setState({ isShown: true }, () => {
      this.closeButton.focus();
    });
    
  };
  closeModal = () => {
    this.setState({ isShown: false });    
    
  };
  onKeyDown = (event) => {
    if (event.keyCode === 27) {
      this.closeModal();
    }
  };
  onClickOutside = (event) => {
    if (this.modal && this.modal.contains(event.target)) return;
    this.closeModal();
  };

  onSubmit = (event) => {
    event.preventDefault(event);
    console.log(event.target.name.value);
    console.log(event.target.description.value);

  const url = `https://www.flickr.com/services/rest/?method=flickr.galleries.create&api_key=${config.apiKey}&title=${event.target.name.value}&description=${event.target.description.value}&format=json&nojsoncallback=1&content_type=1`;
  axios.post(url)
    .then(res => {
      console.log(res);
      console.log(res.data);
    })

    this.closeModal();
  };

  render() {
    return (
      <React.Fragment>
        <TriggerNewGalleryButton
          showModal={this.showModal}
          buttonRef={(n) => (this.TriggerButton = n)}
          triggerText={this.props.triggerText}
        />
        {this.state.isShown ? (
          <NewGalleryModal
            onSubmit={this.onSubmit}
            modalRef={(n) => (this.modal = n)}
            buttonRef={(n) => (this.closeButton = n)}
            closeModal={this.closeModal}
            onKeyDown={this.onKeyDown}
            onClickOutside={this.onClickOutside}
          />
        ) : null}
      </React.Fragment>
    );
  }
}

export default AddGallery;
