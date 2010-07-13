package plupload.client;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;

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

	void onFileUploaded(Plupload p, File file, JavaScriptObject response);

	void onChunkUploaded(Plupload p, File file, JavaScriptObject response);

	void onError(Plupload p, JavaScriptObject error);

}
