package plupload.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public final class Plupload extends JavaScriptObject {
	static native Plupload create(JavaScriptObject settings) /*-{
		return new $wnd.plupload.Uploader(settings);
	}-*/;

	protected Plupload() {
	}

	public native void init() /*-{
		this.init();
	}-*/;

	public native void refresh() /*-{
		this.refresh();
	}-*/;

	public native String getId() /*-{
		this.id;
	}-*/;

	public PluploadState getState() {
		return PluploadState.values()[getStateNr() - 1];
	}

	private native int getStateNr() /*-{
		this.id;
	}-*/;

	public native QueueProgress getTotal() /*-{
		this.total;
	}-*/;

	public List<File> getFiles() {
		return asList(getFileList(), File.class);
	}

	private native JsArray<File> getFileList()/*-{
		return this.files();
	}-*/;

	public native File getFile(String id) /*-{
		return this.getFile(id);
	}-*/;

	public native void removeFile(File file) /*-{
		this.removeFile(file);
	}-*/;

	public final List<File> remove(int start, int count) {
		return asList(splice(start, count), File.class);
	}

	private native JsArray<File> splice(int start, int count) /*-{
		return this.splice(start, count);
	}-*/;

	public native void bind(String name, JavaScriptObject func) /*-{
		return this.bind(name, func);
	}-*/;

	public native void unbind(String name, JavaScriptObject func) /*-{
		return this.unbind(name, func);
	}-*/;

	public native void start() /*-{
		this.start();
	}-*/;

	public native void stop() /*-{
		this.stop();
	}-*/;

	@SuppressWarnings("unchecked")
	static <T> List<T> asList(JsArray array, Class<T> t) {
		List<T> result = new ArrayList<T>();
		for (int index = 0; index < array.length(); index++)
			result.add((T) array.get(index));
		return result;
	}
}
