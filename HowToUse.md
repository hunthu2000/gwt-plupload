# How to Use Plupload #

Before starting to use gwt-plupload, first learn how to use [Plupload](http://www.plupload.com/) itself. Gwt-plupload is only a wrapper to the properties and callbacks Plupload offers, so it is important to understand how it works.

# How to Use gwt-plupload #

In a nutshell, this is how Plupload is integrated into GWT application:

1. Create your file uploader UI as you like. For the add files button (can be any element, such as label or plain div), assign some specific id:

```
browseButton.getElement().setId("my-browse-button");
```

2. Create your plupload with gwt-plupload builder

```
PluploadBuilder builder = new PluploadBuilder();
// ADD ANY PLUPLOAD PROPERTIES HERE
builder.uploadUrl("server/upload.php");
builder.browseButton("my-browse-button");
Plupload plupload = builder.create();
```

3. After user has added the files to be uploaded, start the upload

```
plupload.start();
```

## How to Use Builder ##

Builder is a fluent builder, which means you can chain the properties:

```
Plupload plupload = new PluploadBuilder().uploadUrl("server/upload.php").browseButton("my-browse-button").create();
```

Builder has following API:

  * `runtime(String runtime)`; Add one runtime to runtime list

  * `runtimes(String runtimes)`: Set the entire runtime list

  * `flashUrl(String url)`: Set the url to the Flash component

  * `silverlightUrl(String url)`: Set the url to the Silverlight component

  * `uploadUrl(String uploadUrl)`: Set the url to the server handling the upload

  * `filter(String title, String extensions)`: Add a file filter

  * `browseButton(String browseButtonId)`: Set the element id for adding files

  * `dragAndDropTarget(String targetId)`: Set the element id for drag&drop

  * `maxFileSize(String size)`: Set the maximum allowed file size

  * `chunk(String size)`: Set the size of a chunk in chunked upload

  * `listener(PluploadListener listener)`: Set the listener for Plupload callbacks

  * `create(void)`: Create Plupload instance

The API for the Plupload objects are identical with the [Plupload native API](http://www.plupload.com/plupload/docs/api/index.html).

To listen events from Plupload, register Plupload listener implementation to the builder:

```
public interface PluploadListener {

	void onInit(Plupload p, String runtime);

	void postInit(Plupload p);

	void onFilesAdded(Plupload p, List<File> files);

	void onFilesRemoved(Plupload p, List<File> files);

	void onQueueChanged(Plupload p);

	void onRefresh(Plupload p);

	void onStateChanged(Plupload p);

	void onFileUpload(Plupload p, File file);

	void onFileUploadProgress(Plupload p, File file);

}
```