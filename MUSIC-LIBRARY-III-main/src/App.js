import React, { useState } from 'react';
import { Route, Routes } from 'react-router-dom';
import SearchResults from './components/Searchresults';
import Header from './components/header';
import SongDetail from './components/SongDetail';



function App() {
  const [artist, setArtist] = useState('');

  return (
    <div className="App">
      <Header appName="My Library Music"/>
        <section className="main">
          <div className='songs-section'>
            <Routes>
              <Route path='/SongDetail/:id' element={<SongDetail artist={artist}/>}/>
              <Route path='/' element={<SearchResults setArtist={setArtist} artist={artist} className="search-results" />} />
            </Routes>
          </div>
        </section>
      
    </div>
  );
}


export default App;