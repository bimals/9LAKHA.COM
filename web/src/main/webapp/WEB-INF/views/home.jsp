<!-- Header Carousel -->
  <header id="myCarousel" class="carousel slide interval" >
      <!-- Indicators -->
      <ol class="carousel-indicators">
          <li data-target="#/myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#/myCarousel" data-slide-to="1"></li>
          <li data-target="#/myCarousel" data-slide-to="2"></li>
          <li data-target="#/myCarousel" data-slide-to="3"></li>
          <li data-target="#/myCarousel" data-slide-to="4"></li>
      </ol>

      <!-- Wrapper for slides -->
      <div class="carousel-inner">
          <div class="item active">
              <div class="fill" style="background-color:blue;"></div>
              <div class="carousel-caption">
                  <h2>BAHIKHAATA.COM</h2>
              </div>
          </div>
          <div class="item">
              <div class="fill" style="background-color:red;"></div>
              <div class="carousel-caption">
                  <h2>JEWELHAAT.COM</h2>
              </div>
          </div>
          <div class="item">
              <div class="fill" style="background-color:green;"></div>
              <div class="carousel-caption">
                  <h2>BABAJIKIGHANTI.COM</h2>
              </div>
          </div>
          <div class="item">
              <div class="fill" style="background-color:yellow;"></div>
              <div class="carousel-caption">
                  <h2>BSAHAY.ORG</h2>
              </div>
          </div>
          <div class="item">
              <div class="fill" style="background-color:orange;"></div>
              <div class="carousel-caption">
                  <h2>CHAALOOKHAATA.COM</h2>
              </div>
          </div>          
      </div>

      <!-- Controls -->
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">
          <span class="icon-prev"></span>
      </a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next">
          <span class="icon-next"></span>
      </a>
  </header>

<div ng-view></div>