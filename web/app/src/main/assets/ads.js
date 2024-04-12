let videoElement;
let adsLoaded = false;
let adContainer;
let adDisplayContainer;
let adsLoader;
let adsManager;
//Set from native code
let endpoints; 
let endpointIdx = 0;

function startAds() {
  videoElement = document.getElementById('video-element');
  adContainer = document.getElementById('ad-container');
  initializeIMA();  
}

function initializeIMA() {
  console.log("initializing IMA");

  adDisplayContainer = new google.ima.AdDisplayContainer(adContainer, videoElement);
  adsLoader = new google.ima.AdsLoader(adDisplayContainer);

  adsLoader.addEventListener(
    google.ima.AdsManagerLoadedEvent.Type.ADS_MANAGER_LOADED,
    onAdsManagerLoaded,
    false);
  adsLoader.addEventListener(
    google.ima.AdErrorEvent.Type.AD_ERROR,
    onAdError,
    false);

  var adsRequest = new google.ima.AdsRequest();
  adsRequest.adTagUrl = endpoints[endpointIdx];
  endpointIdx = (endpointIdx + 1) % endpoints.length;
  adsLoader.requestAds(adsRequest);
}

function loadAds(event) {
  if (adsLoaded) {
    return;
  }
  adsLoaded = true;

  console.log("loading ads");

  adDisplayContainer.initialize();

  new google.ima.AdsRenderingSettings();

  var width = videoElement.clientWidth;
  var height = videoElement.clientHeight;
  try {
    adsManager.init(width, height, google.ima.ViewMode.NORMAL);
    adsManager.start();
  } catch (adError) {
    console.log("AdsManager could not be started");
    console.log(adError);
    videoElement.play();
  }
}


function onAdsManagerLoaded(adsManagerLoadedEvent) {
  const adsRenderingSettings = new google.ima.AdsRenderingSettings();
  adsRenderingSettings.uiElements = [];

  adsManager = adsManagerLoadedEvent.getAdsManager(
    videoElement, adsRenderingSettings);
  adsManager.addEventListener(
    google.ima.AdErrorEvent.Type.AD_ERROR,
    onAdError);
  adsManager.addEventListener(
    google.ima.AdEvent.Type.LOADED,
    onAdLoaded);
  adsManager.addEventListener(
    google.ima.AdEvent.Type.ALL_ADS_COMPLETED,
    onAllAdsCompleted);
    
 loadAds(); 
}

function onAllAdsCompleted() {
  console.log("all ads completed");
  adsLoaded = false;
  initializeIMA();
}

function onAdError(adErrorEvent) {
  console.log(adErrorEvent.getError());
  if (adsManager) {
    adsManager.destroy();
  }
}

function onAdLoaded(adEvent) {
  var ad = adEvent.getAd();
  if (!ad.isLinear()) {
    videoElement.play();
  }
}