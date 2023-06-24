import React from 'react'
import "./home.css"
import SplitScreen from '../../layouts/SplitScreen'
import MainFocus from '../../components/MainFocus/MainFocus'


const Home = () => {
  return (
    <>

      <SplitScreen leftWeight={1} rightWeight={2}   >
        <MainFocus >
        <h5>fdfd</h5>
        <h1>fdfd</h1>
        <h1>fdfd</h1>
        <h1>fdfd</h1>
        <h1>fdfd</h1>
        <h1>fdfd</h1>
        <h1>fdfd</h1>
        </MainFocus>  
         <MainFocus >
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>
         <h1>fdfd</h1>


        </MainFocus>
      </SplitScreen>

    </>
  )
}

export default Home