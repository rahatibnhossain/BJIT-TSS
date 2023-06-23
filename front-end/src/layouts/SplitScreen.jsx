import React from 'react'
import styled from 'styled-components'

const Container = styled.div`
  display: flex;
  flex-direction: row;
  @media (max-width: 768px) {
    flex-direction: column;
  }
`;
const Pane = styled.div`
  flex:${props => props.weight};
`;

const SplitScreen = ({
  children,
  leftWeight,
  rightWeight

}) => {


  const [left, right] = children
  return (
    <Container>
      <Pane weight={leftWeight}>
        {left}
      </Pane>
      <Pane weight={rightWeight}>
        {right}
      </Pane>
    </Container>
  )
}

export default SplitScreen