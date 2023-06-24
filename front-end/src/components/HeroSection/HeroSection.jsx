import React from 'react'
import './herosection.css'


const HeroSection = ({children}) => {
  return (
    <section className='hero-section'>{children}</section>
  )
}

export default HeroSection