package plupload.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class PluploadBuilder {
	private JavaScriptObject settings = JavaScriptObject.createObject();
	private PluploadListener listener = null;

	public PluploadBuilder runtimes(String runtimes) {
		set("runtimes", runtimes);
		return this;
	}

	public PluploadBuilder flashUrl(String url) {
		set("flash_swf_url", url);
		return this;
	}

	public PluploadBuilder silverlightUrl(String url) {
		set("silverlight_xap_url", url);
		return this;
	}

	public PluploadBuilder uploadUrl(String uploadUrl) {
		set("url", uploadUrl);
		return this;
	}

	public PluploadBuilder filter(String title, String extensions) {
		addFilters(title, extensions);
		return this;
	}

	public PluploadBuilder browseButton(String browseButtonId) {
		set("browse_button", browseButtonId);
		return this;
	}

	public PluploadBuilder maxFileSize(String size) {
		set("max_file_size", size);
		return this;
	}

	public PluploadBuilder chunk(String size) {
		set("chunk_size", size);
		return this;
	}

	public PluploadBuilder listener(PluploadListener listener) {
		this.listener = listener;
		return this;
	}

	public Plupload create() {
		Plupload uploader = Plupload.create(settings);
		if (listener != null)
			bindListener(uploader, listener);
		return uploader;
	}

	private void bindListener(Plupload uploader, final PluploadListener listener) {
		uploader.bind("Init", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onInit(pl, getString(p, "runtime"));
			}
		}));

		uploader.bind("PostInit", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.postInit(pl);
			}
		}));

		uploader.bind("FilesAdded", createFunc(new Callback() {
			@SuppressWarnings("unchecked")
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onFilesAdded(pl, Plupload.asList((JsArray) p.cast(),
						File.class));
			}
		}));

		uploader.bind("FilesRemoved", createFunc(new Callback() {
			@SuppressWarnings("unchecked")
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onFilesRemoved(pl, Plupload.asList((JsArray) p.cast(),
						File.class));
			}
		}));

		uploader.bind("QueueChanged", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onQueueChanged(pl);
			}
		}));

		uploader.bind("Refresh", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onRefresh(pl);
			}
		}));

		uploader.bind("StateChanged", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onStateChanged(pl);
			}
		}));

		uploader.bind("UploadFile", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onFileUpload(pl, (File) p.cast());
			}
		}));

		uploader.bind("UploadProgress", createFunc(new Callback() {
			@Override
			public void onCallback(Plupload pl, JavaScriptObject p) {
				listener.onFileUploadProgress(pl, (File) p.cast());
			}
		}));
	}

	private native JavaScriptObject createFunc(Callback callback) /*-{
		return function(uploader, p) {
			@plupload.client.PluploadBuilder::fireCallback(Lplupload/client/PluploadBuilder$Callback;Lplupload/client/Plupload;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, uploader, p);
		};
	}-*/;

	private native void set(String name, String value) /*-{
		this.@plupload.client.PluploadBuilder::settings[name] = value;
	}-*/;

	private native void addFilters(String title, String extensions) /*-{
		if (!this.@plupload.client.PluploadBuilder::settings['filters'])
			this.@plupload.client.PluploadBuilder::settings['filters'] = [];
		this.@plupload.client.PluploadBuilder::settings['filters'].push({title:title, extensions: extensions});
	}-*/;

	@SuppressWarnings("unused")
	private static void fireCallback(Callback cb, Plupload pl,
			JavaScriptObject p) {
		cb.onCallback(pl, p);
	}

	public interface Callback {
		void onCallback(Plupload pl, JavaScriptObject p);
	}

	protected native String getString(JavaScriptObject p, String name) /*-{
		return p[name];
	}-*/;

	public JavaScriptObject getSettings() {
		return settings;
	}
}
